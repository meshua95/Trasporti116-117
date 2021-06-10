package viewCallCenter.dialog;

import digitalTwins.transport.TransportDigitalTwin;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

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
