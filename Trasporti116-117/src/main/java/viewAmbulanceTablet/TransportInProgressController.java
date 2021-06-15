package viewAmbulanceTablet;

import digitalTwinsAPI.TransportEnded;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class TransportInProgressController implements Initializable {
    @FXML
    private Label transportLabel;
    @FXML
    private Button stopBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (MainAppAmbulanceTablet.getTransportId().isPresent())
            transportLabel.setText(MainAppAmbulanceTablet.getTransportId().get().getId());
        else{
            new Alert(Alert.AlertType.ERROR, "Nessun trasporto selezionato", ButtonType.CLOSE).show();
            MainAppAmbulanceTablet.setScene(SceneTypeAmbulanceTablet.BOOKING_SCENE);
        }

        stopBtn.setOnAction(event -> {
            if (MainAppAmbulanceTablet.getTransportId().isPresent()){
                TransportEnded.setTransportEnded(MainAppAmbulanceTablet.getTransportId().get());
                MainAppAmbulanceTablet.setScene(SceneTypeAmbulanceTablet.BOOKING_SCENE);
            }
        });
    }
}