/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package viewAmbulanceTablet;

import digitalTwinsAPI.GetAmbulance;
import digitalTwinsAPI.GetOperator;
import domain.transport.ambulance.AmbulanceId;
import domain.transport.operator.OperatorId;
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
        GetOperator.getAllOperatorId().forEach(o -> operatorList.getItems().add(o.getOperatorId()));
        GetAmbulance.getAllAmbulanceIdTwins().forEach(a -> ambulanceList.getItems().add(a.getAmbulanceId()));
        ok.setOnAction(event -> {
            MainAppAmbulanceTablet.setOperatorAndAmbulance(new OperatorId(operatorList.getValue()), new AmbulanceId(ambulanceList.getValue()));
            MainAppAmbulanceTablet.setScene(SceneTypeAmbulanceTablet.BOOKING_SCENE);
        });
    }
}