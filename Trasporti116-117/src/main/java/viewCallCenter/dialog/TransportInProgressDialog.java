package viewCallCenter.dialog;

import digitalTwinsAPI.GetTransport;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;

public class TransportInProgressDialog extends DtDialog {

    @Override
    public void createEntity() {
        initialize("Trasporti in corso", ButtonType.CLOSE);

        ListView<String> lv = new ListView<>();
        GetTransport.getAllTransportInProgress().forEach(t -> lv.getItems().add(t.getId()));
        gridPane.add(lv, 0, 0);

        dialog.getDialogPane().setContent(gridPane);
        dialog.showAndWait();
    }
}
