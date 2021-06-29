package viewCallCenter;
import com.sothawo.mapjfx.*;
import digitalTwinsAPI.GetAmbulance;
import digitalTwinsAPI.GetGPSCoordinates;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.util.Duration;
import domain.transport.ambulance.AmbulanceId;
import domain.transport.ambulance.Coordinates;
import utils.errorCode.QueryTimeOutException;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static viewCallCenter.SceneTypeCallCenter.ROOT_SCENE;

public class MapsController implements Initializable {
    @FXML
    private MapView mapView;
    @FXML
    private Button back;
    @FXML
    private ComboBox<String> ambulance;
    @FXML
    private Button track;

    Timeline timeline;
    private static final Coordinate cesenaCoordinate = new Coordinate(44.143753271603956 , 12.250847570172596);
    private final Marker ambulanceMarker = Marker.createProvided(Marker.Provided.ORANGE).setVisible(true);

    private static final int INITIAL_ZOOM = 15;

    public void initMaps(Projection projection) {
        mapView.initialize(Configuration.builder()
                .projection(projection)
                .showZoomControls(true)
                .build());

        mapView.setCenter(cesenaCoordinate);
        mapView.setZoom(INITIAL_ZOOM);
    }

    public void clearMaps(){
        ArrayList<AmbulanceId> amb = null;
        try {
            amb = GetAmbulance.getAllAmbulanceIdTwins();
        } catch (QueryTimeOutException e) {
            e.printStackTrace();
        }
        ambulance.getItems().clear();
        assert amb != null;
        amb.forEach(a -> ambulance.getItems().add(a.getAmbulanceId()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        back.setOnAction(event ->  {
            MainAppCallCenter.setScene(ROOT_SCENE);
            resetAmbulance();
        });
        ArrayList<AmbulanceId> amb = null;
        try {
            amb = GetAmbulance.getAllAmbulanceIdTwins();
        } catch (QueryTimeOutException e) {
            e.printStackTrace();
        }
        assert amb != null;
        amb.forEach(a -> ambulance.getItems().add(a.getAmbulanceId()));
        track.setOnAction(event-> {
            resetAmbulance();
            checkGPSPositionChange(new AmbulanceId(ambulance.getValue()));
        });
    }

    private void checkGPSPositionChange(AmbulanceId ambulanceId){
        mapView.addMarker(ambulanceMarker);
        timeline = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        actionEvent -> {
                            Coordinates coordinates = null;
                            try {
                                coordinates = GetGPSCoordinates.getGPSCoordinatesOfAmbulance(ambulanceId);
                            } catch (QueryTimeOutException e) {
                                e.printStackTrace();
                            }
                            assert coordinates != null;
                            final Coordinate newPosition = new Coordinate(coordinates.getLatitude(), coordinates.getLongitude());

                            mapView.setCenter(newPosition);
                            if (ambulanceMarker.getVisible()) {
                                final Coordinate oldPosition = ambulanceMarker.getPosition();
                                ambulanceMarker.setPosition(newPosition);
                                if (oldPosition == null) {
                                    mapView.addMarker(ambulanceMarker);
                                }
                            }
                        }
                ),
                new KeyFrame(
                        Duration.seconds(0.5)
                )
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void resetAmbulance(){
        if (timeline!=null) {
            timeline.stop();
            mapView.removeMarker(ambulanceMarker);
            mapView.setCenter(cesenaCoordinate);
            mapView.setZoom(INITIAL_ZOOM);
        }
    }
}
