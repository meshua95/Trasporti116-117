package view.dialog;

import digitalTwins.request.InfoRequestDigitalTwin;
import digitalTwins.transport.TransportDigitalTwin;
import domain.requestBoundedContext.infoRequest.InfoRequestDescription;
import domain.transportBoundedContext.TransportId;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.time.LocalDateTime;

public class TransportInProgressDialog extends DtDialog {

    @Override
    public void createEntity() {
        initialize("Trasporti in corso", ButtonType.CLOSE);

        Label transport = new Label();
        TransportDigitalTwin.getAllTransportInProgress().forEach(t ->{
            transport.setText(transport.getText() + t.getId() + "\n");

        });

        gridPane.add(transport, 0, 0);

        dialog.getDialogPane().setContent(gridPane);
        dialog.showAndWait();
    }
}
