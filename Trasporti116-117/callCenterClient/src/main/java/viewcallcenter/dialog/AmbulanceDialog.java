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

public class AmbulanceDialog extends DtDialog{

    public void createEntity(){
        initialize("Crea Ambulanza", ButtonType.OK, ButtonType.CANCEL);

        TextField ambulanceNumber = new TextField();
        ambulanceNumber.setPromptText("Numero ambulanza");
        gridPane.add(new Label("Numero ambulanza"), 0, 0);
        gridPane.add(ambulanceNumber, 1, 0);

        dialog.getDialogPane().setContent(gridPane);

        dialog.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> {
                    if (ControllInputField.NUMBER_PATTERN.matcher(ambulanceNumber.getText()).matches()) {
                        String id = CreateAmbulance.createAmbulance(Integer.parseInt(ambulanceNumber.getText()));
                        new Alert(Alert.AlertType.INFORMATION, ControllInputField.AMBULANCE_CONFIRM + id, ButtonType.CLOSE).show();
                    }else
                        new Alert(Alert.AlertType.ERROR, ControllInputField.TEXT_FIELD_ERROR, ButtonType.CLOSE).show();
                });
    }

    public void deleteEntity(){
        initialize("Cancella Ambulanza", ButtonType.OK, ButtonType.CANCEL);

        ComboBox<String> ambulance = new ComboBox<>();
        try {
            GetAmbulance.getAllAmbulanceIdTwins().forEach(a -> ambulance.getItems().add(a.getAmbulanceId()));
        } catch (QueryTimeOutException e) {
            e.printStackTrace();
        }
        gridPane.add(new Label("Ambulanza"), 0, 0);
        gridPane.add(ambulance, 1, 0);

        dialog.getDialogPane().setContent(gridPane);

        dialog.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> {
                    if (DeleteAmbulance.deleteAmbulance(new AmbulanceId(ambulance.getValue())).equals(DeleteDigitalTwinStatusCode.DELETED))
                        new Alert(Alert.AlertType.INFORMATION, ControllInputField.AMBULANCE_DELETED, ButtonType.CLOSE).show();
                });
    }

}
