/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package viewAmbulanceTablet;
import domain.ambulanceBoundedContext.AmbulanceId;
import domain.transportBoundedContext.OperatorId;
import domain.transportBoundedContext.TransportId;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.nio.file.Paths;

import static viewAmbulanceTablet.SceneTypeAmbulanceTablet.ROOT_SCENE;

public class MainAppAmbulanceTablet extends Application {
    private static Stage stage;
    private static Scene rootScene;
    private static Scene bookingScene;
    private static Scene transportInProgressScene;
    private static OperatorId operatorId;
    private static AmbulanceId ambulanceId;
    private static TransportId transportInProgressId;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        Parent root = FXMLLoader.load(Paths.get("src/main/resources/rootSceneAmbulance.fxml").toUri().toURL());
        Parent booking = FXMLLoader.load(Paths.get("src/main/resources/bookingScene.fxml").toUri().toURL());
        Parent transportInProgress = FXMLLoader.load(Paths.get("src/main/resources/transportInProgressScene.fxml").toUri().toURL());

        rootScene = new Scene(root);
        rootScene.getStylesheets().add((Paths.get("src/main/resources/style.css").toUri().toURL()).toExternalForm());

        bookingScene = new Scene(booking);
        bookingScene.getStylesheets().add((Paths.get("src/main/resources/style.css").toUri().toURL()).toExternalForm());

        transportInProgressScene = new Scene(transportInProgress);
        transportInProgressScene.getStylesheets().add((Paths.get("src/main/resources/style.css").toUri().toURL()).toExternalForm());

        stage.setTitle("Trasporti 116-117 ");
        setScene(ROOT_SCENE);
    }

    public static void setScene(SceneTypeAmbulanceTablet type){
        switch (type){
            case BOOKING_SCENE -> stage.setScene(bookingScene);
            case ROOT_SCENE -> stage.setScene(rootScene);
            case TRANSPORT_IN_PROGRESS_SCENE -> stage.setScene(transportInProgressScene);
        }
        stage.show();
    }

    public static void saveOperatorAndAmbulance(OperatorId opId, AmbulanceId ambId){
        ambulanceId = ambId;
        operatorId = opId;
    }

    public static void setTransportInProgressId(TransportId id){
        transportInProgressId = id;
    }

    public static AmbulanceId getAmbulanceId(){
        return ambulanceId;
    }

    public static OperatorId getOperatorId(){
        return operatorId;
    }

    public static TransportId getTransportId(){
        return transportInProgressId;
    }

    public static void main(String[] args) {
        launch(args);
    }

}