package view.dialog;

import domain.ambulanza.AmbulanceDigitalTwin;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.AmbulanceState;

public class AmbulanceDialog {
    public static void createAmbulanzaDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Aggiungi ambulanza");

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        TextField numAmbulanza = new TextField();
        numAmbulanza.setPromptText("Numero ambulanza");

        gridPane.add(new Label("Numero ambulanza"), 0, 0);
        gridPane.add(numAmbulanza, 1, 0);

        dialog.getDialogPane().setContent(gridPane);

        dialog.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> AmbulanceDigitalTwin.createAmbulanza(AmbulanceState.READY, Integer.parseInt(numAmbulanza.getText())));
    }

    public static void removeAmbulanzaDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Rimuovi ambulanza");

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        ComboBox<String> ambulanza = new ComboBox<>();
        AmbulanceDigitalTwin.getAllAmbulanzaIdTwins().forEach(a -> ambulanza.getItems().add(a.getAmbulanceId()));
        gridPane.add(new Label("Ambulanza"), 0, 17);
        gridPane.add(ambulanza, 1, 17);

        dialog.getDialogPane().setContent(gridPane);

        dialog.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> AmbulanceDigitalTwin.deleteAmbulanza(ambulanza.getValue()));

    }

}
