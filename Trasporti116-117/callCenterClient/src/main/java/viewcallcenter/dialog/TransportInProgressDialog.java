package viewcallcenter.dialog;

import digitaltwinsapi.GetTransport;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import utils.errorCode.QueryTimeOutException;

public class TransportInProgressDialog extends DtDialog {

    @Override
    public void createEntity() {
        initialize("Trasporti in corso", ButtonType.CLOSE);

        ListView<String> lv = new ListView<>();
        try {
            GetTransport.getAllTransportInProgress().forEach(t -> lv.getItems().add(t.getId()));
        } catch (QueryTimeOutException e) {
            e.printStackTrace();
        }
        gridPane.add(lv, 0, 0);

        dialog.getDialogPane().setContent(gridPane);
        dialog.showAndWait();
    }
}
