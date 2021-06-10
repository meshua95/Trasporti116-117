/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import view.dialog.*;

import static view.SceneType.MAPS_SCENE;
public class FXMLController implements Initializable {

    @FXML
    private Button addPatient;
    @FXML
    private Button addOperator;
    @FXML
    private Button addAmbulance;
    @FXML
    private Button addServiceRequest;
    @FXML
    private Button addInfoRequest;
    @FXML
    private Button rmAmbulance;
    @FXML
    private Button rmBooking;
    @FXML
    private Button trackAmbulance;
    @FXML
    private Button viewTransport;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        addAmbulance.setOnAction(event -> new AmbulanceDialog().createEntity());
        addPatient.setOnAction(event -> new PatientDialog().createEntity());
        addOperator.setOnAction(event -> new OperatorDialog().createEntity());
        addServiceRequest.setOnAction(event -> new ServiceRequestAndBookingDialog().createEntity());
        addInfoRequest.setOnAction(event -> new InfoRequestDialog().createEntity());
        rmAmbulance.setOnAction(event -> new AmbulanceDialog().deleteEntity());
        rmBooking.setOnAction(event -> new ServiceRequestAndBookingDialog().deleteBooking());
        viewTransport.setOnAction(event -> new TransportInProgressDialog().createEntity());
        trackAmbulance.setOnAction(event -> MainApp.setScene(MAPS_SCENE));
    }
}