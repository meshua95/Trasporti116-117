package viewCallCenter.dialog;
import digitalTwins.ambulance.AmbulanceDigitalTwin;
import javafx.scene.control.*;
import domain.ambulanceBoundedContext.AmbulanceId;

public class AmbulanceDialog extends DtDialog{

    public void createEntity(){
        initialize("Crea Ambulanza", ButtonType.OK, ButtonType.CANCEL);

        TextField numAmbulanza = new TextField();
        numAmbulanza.setPromptText("Numero ambulanza");
        gridPane.add(new Label("Numero ambulanza"), 0, 0);
        gridPane.add(numAmbulanza, 1, 0);

        dialog.getDialogPane().setContent(gridPane);

        dialog.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> AmbulanceDigitalTwin.createAmbulance(Integer.parseInt(numAmbulanza.getText())));
    }

    public void deleteEntity(){
        initialize("Cancella Ambulanza", ButtonType.OK, ButtonType.CANCEL);

        ComboBox<String> ambulanza = new ComboBox<>();
        AmbulanceDigitalTwin.getAllAmbulanceIdTwins().forEach(a -> ambulanza.getItems().add(a.getAmbulanceId()));
        gridPane.add(new Label("Ambulanza"), 0, 0);
        gridPane.add(ambulanza, 1, 0);

        dialog.getDialogPane().setContent(gridPane);

        dialog.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> AmbulanceDigitalTwin.deleteAmbulance(new AmbulanceId(ambulanza.getValue())));
    }

}
