package viewcallcenter.dialog;
import digitaltwinsapi.DeleteAmbulance;
import digitaltwinsapi.CreateAmbulance;
import digitaltwinsapi.GetAmbulance;
import javafx.scene.control.ComboBox;
import domain.transport.ambulance.AmbulanceId;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import utils.errorCode.DeleteDigitalTwinStatusCode;
import utils.errorCode.QueryTimeOutException;
import viewcallcenter.utils.ControllInputField;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class AmbulanceDialog extends DtDialog {

    private static final Logger LOGGER = Logger.getLogger(AmbulanceDialog.class.toString());

    @Override
    public void createEntity() {
        initialize("Crea Ambulanza", ButtonType.OK, ButtonType.CANCEL);

        TextField ambulanceNumber = new TextField();
        ambulanceNumber.setPromptText("Numero ambulanza");
        getDtGridPane().add(new Label("Numero ambulanza"), 0, 0);
        getDtGridPane().add(ambulanceNumber, 1, 0);

        getDtDialog().getDialogPane().setContent(getDtGridPane());

        getDtDialog().showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> {
                    if (ControllInputField.NUMBER_PATTERN.matcher(ambulanceNumber.getText()).matches()) {
                        String id = CreateAmbulance.createAmbulance(Integer.parseInt(ambulanceNumber.getText()));
                        new Alert(Alert.AlertType.INFORMATION, ControllInputField.AMBULANCE_CONFIRM + id, ButtonType.CLOSE).show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, ControllInputField.TEXT_FIELD_ERROR, ButtonType.CLOSE).show();
                    }
                });
    }

    public void deleteEntity() {
        initialize("Cancella Ambulanza", ButtonType.OK, ButtonType.CANCEL);

        ComboBox<String> ambulance = new ComboBox<>();
        try {
            GetAmbulance.getAllAmbulanceIdTwins().forEach(a -> ambulance.getItems().add(a.getId()));
        } catch (QueryTimeOutException e) {
            if (LOGGER.isLoggable(Level.SEVERE)) {
                LOGGER.log(Level.SEVERE, e.toString(), e);
            }
        }
        getDtGridPane().add(new Label("Ambulanza"), 0, 0);
        getDtGridPane().add(ambulance, 1, 0);

        getDtDialog().getDialogPane().setContent(getDtGridPane());

        getDtDialog().showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> {
                    if (DeleteAmbulance.deleteAmbulance(new AmbulanceId(ambulance.getValue())).equals(DeleteDigitalTwinStatusCode.DELETED)) {
                        new Alert(Alert.AlertType.INFORMATION, ControllInputField.AMBULANCE_DELETED, ButtonType.CLOSE).show();
                    }
                });
    }

}
