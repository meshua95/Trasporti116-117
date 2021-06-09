/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package viewAmbulanceTablet;
import domain.ambulanceBoundedContext.AmbulanceId;
import domain.requestBoundedContext.serviceRequest.BookingTransportId;
import domain.transportBoundedContext.OperatorId;
import domain.transportBoundedContext.TransportId;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.Optional;

import static viewAmbulanceTablet.SceneTypeAmbulanceTablet.ROOT_SCENE;

public class MainAppAmbulanceTablet extends Application {
    private static Stage stage;
    private static Optional<OperatorId> operatorId = Optional.empty();
    private static Optional<AmbulanceId> ambulanceId = Optional.empty();
    private static Optional<BookingTransportId> bookingTransportId = Optional.empty();

    @Override
    public void start(Stage stage) throws Exception {
        MainAppAmbulanceTablet.stage = stage;
        stage.setTitle("Trasporti 116-117 ");
        setScene(ROOT_SCENE);
    }

    public static void setScene(SceneTypeAmbulanceTablet type)  {
        try {
            switch (type){
                case BOOKING_SCENE:
                    Parent booking = FXMLLoader.load(Paths.get("src/main/resources/bookingScene.fxml").toUri().toURL());
                    Scene bookingScene = new Scene(booking);
                    bookingScene.getStylesheets().add((Paths.get("src/main/resources/style.css").toUri().toURL()).toExternalForm());
                    stage.setScene(bookingScene);
                    break;
                case ROOT_SCENE:
                    Parent root = FXMLLoader.load(Paths.get("src/main/resources/rootSceneAmbulance.fxml").toUri().toURL());
                    Scene rootScene = new Scene(root);
                    rootScene.getStylesheets().add((Paths.get("src/main/resources/style.css").toUri().toURL()).toExternalForm());
                    stage.setScene(rootScene);
                    break;
                case TRANSPORT_IN_PROGRESS_SCENE:
                    Parent transportInProgress = FXMLLoader.load(Paths.get("src/main/resources/transportInProgressScene.fxml").toUri().toURL());
                    Scene transportInProgressScene = new Scene(transportInProgress);
                    transportInProgressScene.getStylesheets().add((Paths.get("src/main/resources/style.css").toUri().toURL()).toExternalForm());
                    stage.setScene(transportInProgressScene);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.show();
    }

    public static void saveOperatorAndAmbulance(OperatorId opId, AmbulanceId ambId){
        ambulanceId = Optional.of(ambId);
        operatorId = Optional.of(opId);
    }

    public static void setTransportInProgressId(BookingTransportId id){
        bookingTransportId = Optional.of(id);
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

    public static void main(String[] args) {
        launch(args);
    }

}