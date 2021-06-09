package view.dialog;

import digitalTwins.booking.BookingDigitalTwin;
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

        ListView<String> lv = new ListView<>();
        TransportDigitalTwin.getAllTransportInProgress().forEach(t -> lv.getItems().add(t.getId()));
        gridPane.add(lv, 0, 0);

        dialog.getDialogPane().setContent(gridPane);
        dialog.showAndWait();
    }
}
