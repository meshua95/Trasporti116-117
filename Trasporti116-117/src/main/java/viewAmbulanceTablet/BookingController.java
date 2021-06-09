package viewAmbulanceTablet;

import digitalTwins.Client;
import digitalTwins.booking.BookingDigitalTwin;
import domain.transportBoundedContext.TransportId;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class BookingController implements Initializable {
    @FXML
    ListView<String> lv;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BookingDigitalTwin.getAllBookingForToday().forEach(System.out::println);
        BookingDigitalTwin.getAllBookingForToday().forEach(b->lv.getItems().add(b.getId()));

        lv.setOnMouseClicked(event -> {
            System.out.println("clicked on " + lv.getSelectionModel().getSelectedItem());
            if(lv.getSelectionModel().getSelectedItem() != null){
                MainAppAmbulanceTablet.setTransportInProgressId(new TransportId(lv.getSelectionModel().getSelectedItem()));
                MainAppAmbulanceTablet.setScene(SceneTypeAmbulanceTablet.TRANSPORT_IN_PROGRESS_SCENE);
            }else{
               // MainAppAmbulanceTablet.setScene(SceneTypeAmbulanceTablet.TRANSPORT_IN_PROGRESS_SCENE);
                new Alert(Alert.AlertType.ERROR, "Seleziona una prenotoazione", ButtonType.CLOSE).show();
            }

        });
    }
}
