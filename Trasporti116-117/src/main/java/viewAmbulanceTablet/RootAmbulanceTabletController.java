/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package viewAmbulanceTablet;

import digitalTwins.ambulance.AmbulanceDigitalTwin;
import digitalTwins.operator.OperatorDigitalTwin;
import domain.ambulanceBoundedContext.AmbulanceId;
import domain.transportBoundedContext.OperatorId;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class RootAmbulanceTabletController implements Initializable {

    @FXML
    private ComboBox<String> ambulanceList;
    @FXML
    private ComboBox<String> operatorList;
    @FXML
    private Button ok;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        OperatorDigitalTwin.getAllOperatorId().forEach(o -> operatorList.getItems().add(o.getOperatorId()));
        AmbulanceDigitalTwin.getAllAmbulanceIdTwins().forEach(a -> ambulanceList.getItems().add(a.getAmbulanceId()));
        ok.setOnAction(event -> {
            MainAppAmbulanceTablet.saveOperatorAndAmbulance(new OperatorId(operatorList.getValue()), new AmbulanceId(ambulanceList.getValue()));
            MainAppAmbulanceTablet.setScene(SceneTypeAmbulanceTablet.BOOKING_SCENE);
        });
    }
}