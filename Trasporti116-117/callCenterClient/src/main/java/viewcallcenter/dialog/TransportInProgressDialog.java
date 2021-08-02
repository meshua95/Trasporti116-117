package viewcallcenter.dialog;

import digitaltwinsapi.GetTransport;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import utils.errorCode.QueryTimeOutException;

public final class TransportInProgressDialog extends DtDialog {

    @Override
    public void createEntity() {
        initialize("Trasporti in corso", ButtonType.CLOSE);

        ListView<String> lv = new ListView<>();
        try {
            GetTransport.getAllTransportInProgress().forEach(t -> lv.getItems().add(t.getId()));
        } catch (QueryTimeOutException e) {
            e.printStackTrace();
        }
        getDtGridPane().add(lv, 0, 0);

        getDtDialog().getDialogPane().setContent(getDtGridPane());
        getDtDialog().showAndWait();
    }
}
