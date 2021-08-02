package viewcallcenter;
import com.sothawo.mapjfx.MapView;
import com.sothawo.mapjfx.Coordinate;
import com.sothawo.mapjfx.Marker;
import com.sothawo.mapjfx.Projection;
import com.sothawo.mapjfx.Configuration;
import digitaltwinsapi.GetAmbulance;
import digitaltwinsapi.GetGPSCoordinates;
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
import java.util.List;
import java.util.ResourceBundle;

import static viewcallcenter.SceneTypeCallCenter.ROOT_SCENE;

public final class MapsController implements Initializable {
    public static final double KEY_FRAME_DURATION = 0.5;
    @FXML
    private MapView mapView;
    @FXML
    private Button back;
    @FXML
    private ComboBox<String> ambulance;
    @FXML
    private Button track;

    private Timeline timeline;

    private static final Coordinate CESENA_COORDINATE = new Coordinate(44.143753271603956, 12.250847570172596);
    private final Marker ambulanceMarker = Marker.createProvided(Marker.Provided.ORANGE).setVisible(true);

    private static final int INITIAL_ZOOM = 15;

    public void initMaps(final Projection projection) {
        mapView.initialize(Configuration.builder()
                .projection(projection)
                .showZoomControls(true)
                .build());

        mapView.setCenter(CESENA_COORDINATE);
        mapView.setZoom(INITIAL_ZOOM);
    }

    public void clearMaps() {
        List<AmbulanceId> amb = null;
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
    public void initialize(final URL location, final ResourceBundle resources) {
        back.setOnAction(event ->  {
            MainAppCallCenter.setScene(ROOT_SCENE);
            resetAmbulance();
        });

        List<AmbulanceId> amb = null;
        try {
            amb = GetAmbulance.getAllAmbulanceIdTwins();
        } catch (QueryTimeOutException e) {
            e.printStackTrace();
        }
        assert amb != null;
        amb.forEach(a -> ambulance.getItems().add(a.getAmbulanceId()));
        track.setOnAction(event -> {
            resetAmbulance();
            checkGPSPositionChange(new AmbulanceId(ambulance.getValue()));
        });
    }

    private void checkGPSPositionChange(final AmbulanceId ambulanceId) {
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
                        Duration.seconds(KEY_FRAME_DURATION)
                )
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void resetAmbulance() {
        if (timeline != null) {
            timeline.stop();
            mapView.removeMarker(ambulanceMarker);
            mapView.setCenter(CESENA_COORDINATE);
            mapView.setZoom(INITIAL_ZOOM);
        }
    }
}
