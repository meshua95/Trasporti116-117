/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */


package viewcallcenter;

import com.sothawo.mapjfx.Projection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

import static viewcallcenter.SceneTypeCallCenter.ROOT_SCENE;

public final class MainAppCallCenter extends Application {
    private static Stage stage;
    private static Scene rootScene;
    private static Scene mapsScene;
    private static MapsController controller;

    @Override
    public void start(final Stage s) throws Exception {
        MainAppCallCenter.stage = s;
        FXMLLoader fxmlLoader = new FXMLLoader();

        Parent root = FXMLLoader.load(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("rootSceneCallCenter.fxml")));
        Parent maps = fxmlLoader.load(getClass().getResourceAsStream("/mapScene.fxml"));

        rootScene = new Scene(root);
        rootScene.getStylesheets().add(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("style.css")).toString());

        controller = fxmlLoader.getController();
        final Projection projection = getParameters().getUnnamed().contains("wgs84")
                ? Projection.WGS_84 : Projection.WEB_MERCATOR;
        controller.initMaps(projection);

        stage.setTitle("Trasporti 116-117 ");

        mapsScene = new Scene(maps);
        mapsScene.getStylesheets().add(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("style.css")).toString());
        setScene(ROOT_SCENE);
    }

    public static void setScene(final SceneTypeCallCenter type) {
        switch (type) {
            case MAPS_SCENE:
                controller.clearMaps();
                stage.setScene(mapsScene);
                break;
            default:
                stage.setScene(rootScene);
                break;
        }
        stage.show();
    }

    public static void main(final String[] args) {
        launch(args);
    }

}
