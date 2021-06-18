/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */


package viewCallCenter;

import com.sothawo.mapjfx.Projection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.nio.file.Paths;

import static viewCallCenter.SceneTypeCallCenter.ROOT_SCENE;

public class MainAppCallCenter extends Application {
    private static Stage stage;
    private static Scene rootScene;
    private static Scene mapsScene;
    private static MapsController controller;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader();

        Parent root = FXMLLoader.load(Paths.get("src/main/resources/rootSceneCallCenter.fxml").toUri().toURL());
        Parent maps = fxmlLoader.load(getClass().getResourceAsStream("/mapScene.fxml"));

        rootScene = new Scene(root);
        rootScene.getStylesheets().add((Paths.get("src/main/resources/style.css").toUri().toURL()).toExternalForm());

        controller = fxmlLoader.getController();
        final Projection projection = getParameters().getUnnamed().contains("wgs84")
                ? Projection.WGS_84 : Projection.WEB_MERCATOR;
        controller.initMaps(projection);

        stage.setTitle("Trasporti 116-117 ");

        mapsScene = new Scene(maps);
        mapsScene.getStylesheets().add((Paths.get("src/main/resources/style.css").toUri().toURL()).toExternalForm());
        setScene(ROOT_SCENE);
    }

    public static void setScene(SceneTypeCallCenter type){
        switch (type){
            case MAPS_SCENE:
                controller.clearMaps();
                stage.setScene(mapsScene);
                break;
            case ROOT_SCENE:
                stage.setScene(rootScene);
                break;
        }
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}