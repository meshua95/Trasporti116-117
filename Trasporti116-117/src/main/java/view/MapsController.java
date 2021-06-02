package view;
import com.sothawo.mapjfx.*;
import com.sothawo.mapjfx.event.MapViewEvent;
import domain.ambulanza.AmbulanceDigitalTwin;
import javafx.animation.AnimationTimer;
import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import model.AmbulanceId;
import model.Coordinates;

import java.io.Serial;
import java.net.URL;
import java.util.ResourceBundle;

import static view.SceneType.MAPS_SCENE;
import static view.SceneType.ROOT_SCENE;

public class MapsController implements Initializable {
    @FXML
    private MapView mapView;
    @FXML
    private Button back;
    private static final Coordinate coordKarlsruheHarbour = new Coordinate(44.143753271603956 , 12.250847570172596);
    private Marker markerKaHarbour;
    private Marker markerClick = Marker.createProvided(Marker.Provided.ORANGE).setVisible(true);
    private MapCircle circleCastle = new MapCircle(coordKarlsruheHarbour, 1_000).setVisible(true);

    public void initMaps(Projection projection) {
        mapView.setCenter(coordKarlsruheHarbour);
        markerKaHarbour = Marker.createProvided(Marker.Provided.BLUE).setPosition(coordKarlsruheHarbour).setVisible(true);
        mapView.addMarker(markerClick);
        mapView.setZoom(14);


        mapView.addEventHandler(MapViewEvent.MAP_CLICKED, event -> {
            Coordinates coordinates = AmbulanceDigitalTwin.getGPSCoordinatesOfAmbulance(new AmbulanceId(2));
            event.consume();
            final Coordinate newPosition = new Coordinate(coordinates.getLatitude(),coordinates.getLongitude());

            if (markerClick.getVisible()) {
                final Coordinate oldPosition = markerClick.getPosition();
                if (oldPosition != null) {
                    animateClickMarker(oldPosition, newPosition);
                } else {
                    markerClick.setPosition(newPosition);
                    // adding can only be done after coordinate is set

                    mapView.addMarker(markerClick);
                }
            }
        });

        // finally initialize the map view
        mapView.initialize(Configuration.builder()
                .projection(projection)
                .showZoomControls(false)
                .build());




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
                setOnFinished(evt -> markerClick.setPosition(newPosition));
            }

            @Override
            protected void interpolate(double v) {
                final double latitude = oldPosition.getLatitude() + v * deltaLatitude;
                final double longitude = oldPosition.getLongitude() + v * deltaLongitude;
                markerClick.setPosition(new Coordinate(latitude, longitude));
            }
        };
        transition.play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        back.setOnAction(event ->  MainApp.setScene(ROOT_SCENE));
    }
}
