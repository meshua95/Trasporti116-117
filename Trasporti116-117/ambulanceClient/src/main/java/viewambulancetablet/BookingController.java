package viewambulancetablet;

import digitaltwinsapi.GetBooking;
import digitaltwinsapi.SetTakeOwnership;
import digitaltwinsapi.StartTransport;
import domain.request.serviceRequest.BookingTransportId;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import utils.errorCode.QueryTimeOutException;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class BookingController implements Initializable {
    @FXML
    private transient ListView<String> lv;

    private static final Logger LOGGER = Logger.getLogger(BookingController.class.toString());

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        try {
            GetBooking.getAllBookingToDoForTheDay(LocalDateTime.now()).forEach(b -> lv.getItems().add(b.getId()));
        } catch (QueryTimeOutException e) {
            if (LOGGER.isLoggable(Level.SEVERE)) {
                LOGGER.log(Level.SEVERE, e.toString());
            }
        }

        lv.setOnMouseClicked(event -> {
            if (lv.getSelectionModel().getSelectedItem() != null) {
                new Alert(Alert.AlertType.WARNING, "Vuoi davvero iniziare il trasporto?", ButtonType.YES, ButtonType.NO)
                        .showAndWait()
                        .filter(res -> res == ButtonType.YES)
                        .ifPresent(res -> {
                            BookingTransportId bookingId = new BookingTransportId(lv.getSelectionModel().getSelectedItem());
                            MainAppAmbulanceTablet.setBookingId(bookingId);

                            if (MainAppAmbulanceTablet.getAmbulanceId().isPresent() && MainAppAmbulanceTablet.getOperatorId().isPresent()) {
                                try {
                                    SetTakeOwnership.setTakeOwnership(bookingId);
                                    MainAppAmbulanceTablet.setTransportId(
                                            StartTransport.startTransport(
                                                    bookingId,
                                                    MainAppAmbulanceTablet.getAmbulanceId().get(),
                                                    MainAppAmbulanceTablet.getOperatorId().get()));
                                } catch (QueryTimeOutException e) {
                                    if (LOGGER.isLoggable(Level.SEVERE)) {
                                        LOGGER.log(Level.SEVERE, e.toString(), e);
                                    }
                                }
                            }

                            MainAppAmbulanceTablet.setScene(SceneTypeAmbulanceTablet.TRANSPORT_IN_PROGRESS_SCENE);
                        });
            }
        });
    }
}
