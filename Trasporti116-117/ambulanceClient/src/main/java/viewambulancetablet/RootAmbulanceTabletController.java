/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package viewambulancetablet;

import digitaltwinsapi.GetAmbulance;
import digitaltwinsapi.GetOperator;
import domain.transport.ambulance.AmbulanceId;
import domain.transport.operator.OperatorId;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import utils.errorCode.QueryTimeOutException;

import java.net.URL;
import java.util.ResourceBundle;

public final class RootAmbulanceTabletController implements Initializable {

    @FXML
    private ComboBox<String> ambulanceList;
    @FXML
    private ComboBox<String> operatorList;
    @FXML
    private Button ok;

    @Override
    public void  initialize(final URL url, final ResourceBundle rb) {
        try {
            GetOperator.getAllOperatorId().forEach(o -> operatorList.getItems().add(o.getOperatorId()));
        } catch (QueryTimeOutException e) {
            e.printStackTrace();
        }
        try {
            GetAmbulance.getAllAmbulanceIdTwins().forEach(a -> ambulanceList.getItems().add(a.getAmbulanceId()));
        } catch (QueryTimeOutException e) {
            e.printStackTrace();
        }
        ok.setOnAction(event -> {
            MainAppAmbulanceTablet.setOperatorAndAmbulance(new OperatorId(operatorList.getValue()), new AmbulanceId(ambulanceList.getValue()));
            MainAppAmbulanceTablet.setScene(SceneTypeAmbulanceTablet.BOOKING_SCENE);
        });
    }
}
