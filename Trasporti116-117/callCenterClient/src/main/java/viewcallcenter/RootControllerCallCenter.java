/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package viewcallcenter;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import viewcallcenter.dialog.InfoRequestDialog;
import viewcallcenter.dialog.TransportInProgressDialog;
import viewcallcenter.dialog.AmbulanceDialog;
import viewcallcenter.dialog.OperatorDialog;
import viewcallcenter.dialog.PatientDialog;
import viewcallcenter.dialog.ServiceRequestAndBookingDialog;

import static viewcallcenter.SceneTypeCallCenter.MAPS_SCENE;
public final class RootControllerCallCenter implements Initializable {

    @FXML
    private transient Button addPatient;
    @FXML
    private transient Button addOperator;
    @FXML
    private transient Button addAmbulance;
    @FXML
    private transient Button addServiceRequest;
    @FXML
    private transient Button addInfoRequest;
    @FXML
    private transient Button rmAmbulance;
    @FXML
    private transient Button rmBooking;
    @FXML
    private transient Button trackAmbulance;
    @FXML
    private transient Button viewTransport;

    @Override
    public void initialize(final URL url, final ResourceBundle rb) {
        addAmbulance.setOnAction(event -> new AmbulanceDialog().createEntity());
        addPatient.setOnAction(event -> new PatientDialog().createEntity());
        addOperator.setOnAction(event -> new OperatorDialog().createEntity());
        addServiceRequest.setOnAction(event -> new ServiceRequestAndBookingDialog().createEntity());
        addInfoRequest.setOnAction(event -> new InfoRequestDialog().createEntity());
        rmAmbulance.setOnAction(event -> new AmbulanceDialog().deleteEntity());
        rmBooking.setOnAction(event -> new ServiceRequestAndBookingDialog().deleteBooking());
        viewTransport.setOnAction(event -> new TransportInProgressDialog().createEntity());
        trackAmbulance.setOnAction(event -> MainAppCallCenter.setScene(MAPS_SCENE));
    }
}
