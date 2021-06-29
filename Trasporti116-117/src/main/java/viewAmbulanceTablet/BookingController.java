package viewAmbulanceTablet;

import digitalTwinsAPI.GetBooking;
import digitalTwinsAPI.SetTakeOwnership;
import digitalTwinsAPI.StartTransport;
import domain.request.serviceRequest.BookingTransportId;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import utils.errorCode.QueryTimeOutException;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class BookingController implements Initializable {
    @FXML
    ListView<String> lv;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //DeleteBookingTransport.getAllBookingForToday().forEach(e->System.out.println(e.getId()));
        try {
            GetBooking.getAllBookingToDoForTheDay(LocalDateTime.now()).forEach(b->lv.getItems().add(b.getId()));
        } catch (QueryTimeOutException e) {
            e.printStackTrace();
        }

        lv.setOnMouseClicked(event -> {
            if(lv.getSelectionModel().getSelectedItem() != null){
                new Alert(Alert.AlertType.WARNING, "Vuoi davvero iniziare il trasporto?", ButtonType.YES, ButtonType.NO)
                        .showAndWait()
                        .filter(res -> res == ButtonType.YES)
                        .ifPresent(res->{
                            BookingTransportId bookingId = new BookingTransportId(lv.getSelectionModel().getSelectedItem());
                            MainAppAmbulanceTablet.setBookingId(bookingId);

                            if (MainAppAmbulanceTablet.getAmbulanceId().isPresent() && MainAppAmbulanceTablet.getOperatorId().isPresent()) {
                                try {
                                    SetTakeOwnership.setTakeOwnership(bookingId);
                                    MainAppAmbulanceTablet.setTransportId(
                                            StartTransport.startTransport(
                                                    bookingId,
                                                    MainAppAmbulanceTablet.getAmbulanceId().get(),
                                                    MainAppAmbulanceTablet.getOperatorId().get()));
                                } catch (QueryTimeOutException e) {
                                    e.printStackTrace();
                                }
                            }

                            MainAppAmbulanceTablet.setScene(SceneTypeAmbulanceTablet.TRANSPORT_IN_PROGRESS_SCENE);
                        });
            }
        });
    }
}
