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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static viewcallcenter.SceneTypeCallCenter.ROOT_SCENE;

public final class MapsController implements Initializable {
    public static final double KEY_FRAME_DURATION = 0.5;
    @FXML
    private transient MapView mapView;
    @FXML
    private transient Button back;
    @FXML
    private transient ComboBox<String> ambulance;
    @FXML
    private transient Button track;

    private static Timeline timeline;

    private static final Coordinate CESENA_COORDINATE = new Coordinate(44.143753271603956, 12.250847570172596);
    private static final Marker AMBULANCE_MARKER = Marker.createProvided(Marker.Provided.ORANGE).setVisible(true);

    private static final int INITIAL_ZOOM = 15;

    private static final Logger LOGGER = Logger.getLogger(MapsController.class.toString());

    public void initMaps(final Projection projection) {
        mapView.initialize(Configuration.builder()
                .projection(projection)
                .showZoomControls(true)
                .build());

        mapView.setCenter(CESENA_COORDINATE);
        mapView.setZoom(INITIAL_ZOOM);
    }

    public void clearMaps() {
        Optional<List<AmbulanceId>> amb = Optional.empty();
        try {
            amb = Optional.of(GetAmbulance.getAllAmbulanceIdTwins());
        } catch (QueryTimeOutException e) {
            if (LOGGER.isLoggable(Level.SEVERE)) {
                LOGGER.log(Level.SEVERE, e.toString(), e);
            }
        }
        ambulance.getItems().clear();
        amb.ifPresent(ambulanceIds -> ambulanceIds.forEach(a -> ambulance.getItems().add(a.getId())));
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        back.setOnAction(event ->  {
            MainAppCallCenter.setScene(ROOT_SCENE);
            resetAmbulance();
        });

        Optional<List<AmbulanceId>> amb = Optional.empty();
        try {
            amb = Optional.of(GetAmbulance.getAllAmbulanceIdTwins());
        } catch (QueryTimeOutException e) {
            if (LOGGER.isLoggable(Level.SEVERE)) {
                LOGGER.log(Level.SEVERE, e.toString(), e);
            }
        }
        amb.ifPresent(ambulanceIds -> ambulanceIds.forEach(a -> ambulance.getItems().add(a.getId())));

        track.setOnAction(event -> {
            resetAmbulance();
            checkGPSPositionChange(new AmbulanceId(ambulance.getValue()));
        });
    }

    private void checkGPSPositionChange(final AmbulanceId ambulanceId) {
        mapView.addMarker(AMBULANCE_MARKER);
        timeline = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        actionEvent -> {
                            Optional<Coordinates> coordinates = Optional.empty();
                            try {
                                coordinates = Optional.of(GetGPSCoordinates.getGPSCoordinatesOfAmbulance(ambulanceId));
                            } catch (QueryTimeOutException e) {
                                if (LOGGER.isLoggable(Level.SEVERE)) {
                                    LOGGER.log(Level.SEVERE, e.toString(), e);
                                }
                            }
                            final Coordinate newPosition = new Coordinate(coordinates.get().getLatitude(), coordinates.get().getLongitude());

                            mapView.setCenter(newPosition);
                            if (AMBULANCE_MARKER.getVisible()) {
                                final Coordinate oldPosition = AMBULANCE_MARKER.getPosition();
                                AMBULANCE_MARKER.setPosition(newPosition);
                                if (oldPosition == null) {
                                    mapView.addMarker(AMBULANCE_MARKER);
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
            mapView.removeMarker(AMBULANCE_MARKER);
            mapView.setCenter(CESENA_COORDINATE);
            mapView.setZoom(INITIAL_ZOOM);
        }
    }
}
