package viewAmbulanceTablet;

import digitalTwins.booking.BookingDigitalTwin;
import domain.requestBoundedContext.serviceRequest.BookingTransportId;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import java.net.URL;
import java.util.ResourceBundle;

public class BookingController implements Initializable {
    @FXML
    ListView<String> lv;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //BookingDigitalTwin.getAllBookingForToday().forEach(e->System.out.println(e.getId()));
        BookingDigitalTwin.getAllBookingForToday().forEach(b->lv.getItems().add(b.getId()));

        lv.setOnMouseClicked(event -> {
            if(lv.getSelectionModel().getSelectedItem() != null){
                new Alert(Alert.AlertType.WARNING, "Vuoi davvero iniziare il trasporto?", ButtonType.YES, ButtonType.NO)
                        .showAndWait()
                        .filter(res -> res == ButtonType.YES)
                        .ifPresent(res->{
                            BookingTransportId bookingId = new BookingTransportId(lv.getSelectionModel().getSelectedItem());
                            MainAppAmbulanceTablet.setTransportInProgressId(bookingId);
                            //TransportDigitalTwin.startTransport(bookingId);
                            MainAppAmbulanceTablet.setScene(SceneTypeAmbulanceTablet.TRANSPORT_IN_PROGRESS_SCENE);
                        });

            }
        });
    }
}
