package viewCallCenter;
import com.sothawo.mapjfx.*;
import digitalTwins.ambulance.AmbulanceDigitalTwin;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.util.Duration;
import domain.ambulanceBoundedContext.AmbulanceId;
import domain.ambulanceBoundedContext.Coordinates;

import java.net.URL;
import java.util.ResourceBundle;

import static viewCallCenter.SceneTypeCallCenter.ROOT_SCENE;

public class MapsController implements Initializable {
    @FXML
    private MapView mapView;
    @FXML
    private Button back;
    @FXML
    private ComboBox ambulance;
    @FXML
    private Button track;

    Timeline timeline;
    private static final Coordinate cesenaCoordinate = new Coordinate(44.143753271603956 , 12.250847570172596);
    private Marker ambulanceMarker = Marker.createProvided(Marker.Provided.ORANGE).setVisible(true);

    private static final int INITIAL_ZOOM = 15;

    public void initMaps(Projection projection) {
        mapView.initialize(Configuration.builder()
                .projection(projection)
                .showZoomControls(true)
                .build());

        mapView.setCenter(cesenaCoordinate);
        mapView.setZoom(INITIAL_ZOOM);
    }

    private void animateClickMarker(Coordinate oldPosition, Coordinate newPosition) {
        // animate the marker to the new position
        final Transition transition = new Transition() {
            private final Double oldPositionLongitude = oldPosition.getLongitude();
            private final Double oldPositionLatitude = oldPosition.getLatitude();
            private final double deltaLatitude = newPosition.getLatitude() - oldPositionLatitude;
            private final double deltaLongitude = newPosition.getLongitude() - oldPositionLongitude;

            {
                setCycleDuration(Duration.seconds(1.0));
                setOnFinished(evt -> ambulanceMarker.setPosition(newPosition));
            }

            @Override
            protected void interpolate(double v) {
                final double latitude = oldPosition.getLatitude() + v * deltaLatitude;
                final double longitude = oldPosition.getLongitude() + v * deltaLongitude;
                ambulanceMarker.setPosition(new Coordinate(latitude, longitude));
            }
        };
        transition.play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        back.setOnAction(event ->  {
            MainAppCallCenter.setScene(ROOT_SCENE);
            resetAmbulance();
        });
        AmbulanceDigitalTwin.getAllAmbulanceIdTwins().forEach(a -> ambulance.getItems().add(a.getAmbulanceId()));
        track.setOnAction(event-> {
            resetAmbulance();
            checkGPSPositionChange(new AmbulanceId(ambulance.getValue().toString()));
        });
    }

    private void checkGPSPositionChange(AmbulanceId ambulanceId){
        mapView.addMarker(ambulanceMarker);
        timeline = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        actionEvent -> {
                            Coordinates coordinates = AmbulanceDigitalTwin.getGPSCoordinatesOfAmbulance(ambulanceId);
                            final Coordinate newPosition = new Coordinate(coordinates.getLatitude(), coordinates.getLongitude());

                            mapView.setCenter(newPosition);
                            if (ambulanceMarker.getVisible()) {
                                final Coordinate oldPosition = ambulanceMarker.getPosition();
                                if (oldPosition != null) {
                                    ambulanceMarker.setPosition(newPosition);
                                } else {
                                    ambulanceMarker.setPosition(newPosition);
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