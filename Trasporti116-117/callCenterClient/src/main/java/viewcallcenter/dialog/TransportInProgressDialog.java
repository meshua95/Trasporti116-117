package viewcallcenter.dialog;

import digitaltwinsapi.GetTransport;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import utils.errorCode.QueryTimeOutException;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class TransportInProgressDialog extends DtDialog {
    private static final Logger LOGGER = Logger.getLogger(TransportInProgressDialog.class.toString());

    @Override
    public void createEntity() {
        initialize("Trasporti in corso", ButtonType.CLOSE);

        ListView<String> lv = new ListView<>();
        try {
            GetTransport.getAllTransportInProgress().forEach(t -> lv.getItems().add(t.getId()));
        } catch (QueryTimeOutException e) {
            if (LOGGER.isLoggable(Level.SEVERE)) {
                LOGGER.log(Level.SEVERE, e.toString(), e);
            }
        }
        getDtGridPane().add(lv, 0, 0);

        getDtDialog().getDialogPane().setContent(getDtGridPane());
        getDtDialog().showAndWait();
    }
}
