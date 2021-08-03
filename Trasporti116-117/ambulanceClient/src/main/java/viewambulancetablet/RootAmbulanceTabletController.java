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
import java.util.logging.Level;
import java.util.logging.Logger;

public final class RootAmbulanceTabletController implements Initializable {

    @FXML
    private transient ComboBox<String> ambulanceList;
    @FXML
    private transient ComboBox<String> operatorList;
    @FXML
    private transient Button ok;

    private static final Logger LOGGER = Logger.getLogger(RootAmbulanceTabletController.class.toString());

    @Override
    public void  initialize(final URL url, final ResourceBundle rb) {
        try {
            GetOperator.getAllOperatorId().forEach(o -> operatorList.getItems().add(o.getId()));
        } catch (QueryTimeOutException e) {
            if (LOGGER.isLoggable(Level.SEVERE)) {
                LOGGER.log(Level.SEVERE, e.toString(), e);
            }
        }
        try {
            GetAmbulance.getAllAmbulanceIdTwins().forEach(a -> ambulanceList.getItems().add(a.getId()));
        } catch (QueryTimeOutException e) {
            if (LOGGER.isLoggable(Level.SEVERE)) {
                LOGGER.log(Level.SEVERE, e.toString(), e);
            }
        }
        ok.setOnAction(event -> {
            MainAppAmbulanceTablet.setOperatorAndAmbulance(new OperatorId(operatorList.getValue()), new AmbulanceId(ambulanceList.getValue()));
            MainAppAmbulanceTablet.setScene(SceneTypeAmbulanceTablet.BOOKING_SCENE);
        });
    }
}
