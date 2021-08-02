package viewcallcenter.dialog;

import digitaltwinsapi.CreateRequest;
import domain.request.infoRequest.InfoRequestDescription;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import viewcallcenter.utils.ControllInputField;

import java.time.LocalDateTime;

public class InfoRequestDialog extends DtDialog {

    @Override
    public void createEntity() {
        initialize("Aggiungi Richiesta di informazioni", ButtonType.OK, ButtonType.CANCEL);

        TextArea desc = new TextArea();
        desc.setPromptText("Descrizione della richiesta");
        gridPane.add(new Label("Descrizione"), 0, 0);
        gridPane.add(desc, 1, 0);

        dialog.getDialogPane().setContent(gridPane);
        dialog.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> {
                    String id = CreateRequest.createInfoRequest(
                            LocalDateTime.now(),
                            new InfoRequestDescription(desc.getText())).getInfoRequestId();
                    new Alert(Alert.AlertType.INFORMATION, ControllInputField.INFO_REQUEST_CONFIRM + id, ButtonType.CLOSE).show();
                });

    }
}
