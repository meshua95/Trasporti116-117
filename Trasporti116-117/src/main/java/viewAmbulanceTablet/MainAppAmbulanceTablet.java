/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package viewAmbulanceTablet;
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
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;

import static viewAmbulanceTablet.SceneTypeAmbulanceTablet.ROOT_SCENE;

public class MainAppAmbulanceTablet extends Application {
    private static Stage stage;
    private static Optional<OperatorId> operatorId = Optional.empty();
    private static Optional<AmbulanceId> ambulanceId = Optional.empty();
    private static Optional<BookingTransportId> bookingTransportId = Optional.empty();
    private static Optional<TransportId> transportId = Optional.empty();

    @Override
    public void start(Stage stage) {
        MainAppAmbulanceTablet.stage = stage;
        stage.setTitle("Trasporti 116-117 ");
        setScene(ROOT_SCENE);
    }

    public static void setScene(SceneTypeAmbulanceTablet type)  {
        try {
            switch (type) {
                case BOOKING_SCENE -> {
                    Parent booking = FXMLLoader.load(Objects.requireNonNull(MainAppAmbulanceTablet.class.getClassLoader().getResource("bookingScene.fxml")));
                    Scene bookingScene = new Scene(booking);
                    bookingScene.getStylesheets().add((Objects.requireNonNull(MainAppAmbulanceTablet.class.getClassLoader().getResource("style.css")).toString()));
                    stage.setScene(bookingScene);
                }
                case ROOT_SCENE -> {
                    Parent root = FXMLLoader.load(Objects.requireNonNull(MainAppAmbulanceTablet.class.getClassLoader().getResource("rootSceneAmbulance.fxml")));
                    Scene rootScene = new Scene(root);
                    rootScene.getStylesheets().add((Objects.requireNonNull(MainAppAmbulanceTablet.class.getClassLoader().getResource("style.css")).toString()));
                    stage.setScene(rootScene);
                }
                case TRANSPORT_IN_PROGRESS_SCENE -> {
                    Parent transportInProgress = FXMLLoader.load(Objects.requireNonNull(MainAppAmbulanceTablet.class.getClassLoader().getResource("transportInProgressScene.fxml")));
                    Scene transportInProgressScene = new Scene(transportInProgress);
                    transportInProgressScene.getStylesheets().add(Objects.requireNonNull(MainAppAmbulanceTablet.class.getClassLoader().getResource("style.css")).toString());
                    stage.setScene(transportInProgressScene);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.show();
    }

    public static void setOperatorAndAmbulance(OperatorId opId, AmbulanceId ambId){
        ambulanceId = Optional.of(ambId);
        operatorId = Optional.of(opId);
    }

    public static void setBookingId(BookingTransportId id){
        bookingTransportId = Optional.of(id);
    }

    public static void setTransportId(TransportId id){
        transportId = Optional.of(id);
    }

    public static Optional<AmbulanceId> getAmbulanceId(){
        return ambulanceId;
    }

    public static Optional<OperatorId> getOperatorId(){
        return operatorId;
    }

    public static Optional<BookingTransportId> getBookingTransportId(){
         return bookingTransportId;
    }

    public static Optional<TransportId> getTransportId(){
        return transportId;
    }

    public static void main(String[] args) {
        launch(args);
    }

}