/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */


package view;

import com.sothawo.mapjfx.Projection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.nio.file.Paths;

import static view.SceneType.ROOT_SCENE;

public class MainApp extends Application {
    private static Stage stage;
    private static Scene rootScene;
    private static Scene mapsScene;
    private static Scene transportScene;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader();

        Parent root = FXMLLoader.load(Paths.get("src/main/resources/scene.fxml").toUri().toURL());
        Parent maps = fxmlLoader.load(getClass().getResourceAsStream("/mapScene.fxml"));

        rootScene = new Scene(root);
        rootScene.getStylesheets().add((Paths.get("src/main/resources/style.css").toUri().toURL()).toExternalForm());

        final MapsController controller = fxmlLoader.getController();
        final Projection projection = getParameters().getUnnamed().contains("wgs84")
                ? Projection.WGS_84 : Projection.WEB_MERCATOR;
        controller.initMaps(projection);

        stage.setTitle("Trasporti 116-117 ");

        mapsScene = new Scene(maps);
        mapsScene.getStylesheets().add((Paths.get("src/main/resources/style.css").toUri().toURL()).toExternalForm());
        setScene(ROOT_SCENE);
    }

    public static void setScene(SceneType type){
        switch (type){
            case MAPS_SCENE -> stage.setScene(mapsScene);
            case ROOT_SCENE -> stage.setScene(rootScene);
        }
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}