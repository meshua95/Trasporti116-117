package viewAmbulanceTablet;

import digitalTwins.transport.TransportDigitalTwin;
import domain.requestBoundedContext.serviceRequest.BookingTransportId;
import domain.transportBoundedContext.TransportId;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class TransportInProgressController implements Initializable {
    @FXML
    private Label transportLabel;
    @FXML
    private Button stopBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Optional<BookingTransportId> bookingTransportId= MainAppAmbulanceTablet.getBookingTransportId();
        if (bookingTransportId.isPresent())
            transportLabel.setText(bookingTransportId.get().getId());
        else{
            new Alert(Alert.AlertType.ERROR, "Nessun trasporto selezionato", ButtonType.CLOSE).show();
            MainAppAmbulanceTablet.setScene(SceneTypeAmbulanceTablet.BOOKING_SCENE);
        }

        stopBtn.setOnAction(event -> {
            if (bookingTransportId.isPresent()){
               // TransportDigitalTwin.setTransportEnded(bookingTransportId.get());
                MainAppAmbulanceTablet.setScene(SceneTypeAmbulanceTablet.BOOKING_SCENE);
            }
        });
    }
}