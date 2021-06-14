package viewAmbulanceTablet;

import digitalTwins.booking.BookingDigitalTwin;
import digitalTwins.transport.TransportDigitalTwin;
import domain.requestBoundedContext.serviceRequest.BookingTransportId;
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
        //BookingDigitalTwin.getAllBookingForToday().forEach(e->System.out.println(e.getId()));
        BookingDigitalTwin.getAllBookingToDoForTheDay(LocalDateTime.now()).forEach(b->lv.getItems().add(b.getId()));

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
                                    BookingDigitalTwin.setTakeOwnership(bookingId);
                                    MainAppAmbulanceTablet.setTransportId(
                                            TransportDigitalTwin.startTransport(
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
