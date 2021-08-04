/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package viewambulancetablet;
import domain.transport.ambulance.AmbulanceId;
import domain.request.serviceRequest.BookingTransportId;
import domain.transport.operator.OperatorId;
import domain.transport.TransportId;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static viewambulancetablet.SceneTypeAmbulanceTablet.ROOT_SCENE;

public final class MainAppAmbulanceTablet extends Application {
    private static Stage stage;
    private static Optional<OperatorId> operatorId = Optional.empty();
    private static Optional<AmbulanceId> ambulanceId = Optional.empty();
    private static Optional<BookingTransportId> bookingTransportId = Optional.empty();
    private static Optional<TransportId> transportId = Optional.empty();

    private static final Logger LOGGER = Logger.getLogger(MainAppAmbulanceTablet.class.toString());

    @Override
    public void start(final Stage s) {
        MainAppAmbulanceTablet.stage = s;
        stage.setTitle("Trasporti 116-117 ");
        setScene(ROOT_SCENE);
    }

    public static void setScene(final SceneTypeAmbulanceTablet type)  {
        try {
            switch (type) {
                case BOOKING_SCENE:
                    Parent booking = FXMLLoader.load(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("bookingScene.fxml")));
                    Scene bookingScene = new Scene(booking);
                    bookingScene.getStylesheets().add((Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("style.css")).toString()));
                    stage.setScene(bookingScene);
                    break;

                case TRANSPORT_IN_PROGRESS_SCENE:
                    Parent transportInProgress = FXMLLoader.load(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("transportInProgressScene.fxml")));
                    Scene transportInProgressScene = new Scene(transportInProgress);
                    transportInProgressScene.getStylesheets().add(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("style.css")).toString());
                    stage.setScene(transportInProgressScene);
                    break;

                default:
                    Parent root = FXMLLoader.load(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("rootSceneAmbulance.fxml")));
                    Scene rootScene = new Scene(root);
                    rootScene.getStylesheets().add((Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("style.css")).toString()));
                    stage.setScene(rootScene);
                    break;
            }
        } catch (IOException e) {
            if (LOGGER.isLoggable(Level.SEVERE)) {
                LOGGER.log(Level.SEVERE, e.toString(), e);
            }
        }
        stage.show();
    }

    public static void setOperatorAndAmbulance(final OperatorId opId, final AmbulanceId ambId) {
        ambulanceId = Optional.of(ambId);
        operatorId = Optional.of(opId);
    }

    public static void setBookingId(final BookingTransportId id) {
        bookingTransportId = Optional.of(id);
    }

    public static void setTransportId(final TransportId id) {
        transportId = Optional.of(id);
    }

    public static Optional<AmbulanceId> getAmbulanceId() {
        return ambulanceId;
    }

    public static Optional<OperatorId> getOperatorId() {
        return operatorId;
    }

    public static Optional<BookingTransportId> getBookingTransportId() {
         return bookingTransportId;
    }

    public static Optional<TransportId> getTransportId() {
        return transportId;
    }

    public static void main(final String[] args) {
        launch(args);
    }

}

