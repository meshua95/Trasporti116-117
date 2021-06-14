package viewCallCenter.dialog;
import digitalTwins.ambulance.AmbulanceDigitalTwin;
import javafx.scene.control.*;
import domain.ambulanceBoundedContext.AmbulanceId;
import utils.errorCode.DeleteAmbulanceStatusCode;
import view.utils.ControllInputField;

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
                        String id = AmbulanceDigitalTwin.createAmbulance(Integer.parseInt(ambulanceNumber.getText()));
                        new Alert(Alert.AlertType.INFORMATION, ControllInputField.AMBULANCE_CONFIRM + id, ButtonType.CLOSE).show();
                    }else
                        new Alert(Alert.AlertType.ERROR, ControllInputField.TEXT_FIELD_ERROR, ButtonType.CLOSE).show();
                });
    }

    public void deleteEntity(){
        initialize("Cancella Ambulanza", ButtonType.OK, ButtonType.CANCEL);

        ComboBox<String> ambulance = new ComboBox<>();
        AmbulanceDigitalTwin.getAllAmbulanceIdTwins().forEach(a -> ambulance.getItems().add(a.getAmbulanceId()));
        gridPane.add(new Label("Ambulanza"), 0, 0);
        gridPane.add(ambulance, 1, 0);

        dialog.getDialogPane().setContent(gridPane);

        dialog.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> {
                    if (AmbulanceDigitalTwin.deleteAmbulance(new AmbulanceId(ambulance.getValue())).equals(DeleteAmbulanceStatusCode.DELETED))
                        new Alert(Alert.AlertType.INFORMATION, ControllInputField.AMBULANCE_DELETED, ButtonType.CLOSE).show();
                });
    }

}
