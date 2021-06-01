/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import view.dialog.AmbulanceDialog;
import view.dialog.OperatorDialog;
import view.dialog.PatientDialog;
import view.dialog.TransportDialog;

public class FXMLController implements Initializable {

    @FXML
    private Button addPatient;
    @FXML
    private Button addOperator;
    @FXML
    private Button addAmbulance;
    @FXML
    private Button addTransport;
    @FXML
    private Button rmAmbulance;
    @FXML
    private Button rmTransport;
    @FXML
    private Button trackAmbulance;
    @FXML
    private Button viewTransport;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        addAmbulance.setOnAction(event -> new AmbulanceDialog().createEntity());
        addPatient.setOnAction(event -> new PatientDialog().createEntity());
        addOperator.setOnAction(event -> new OperatorDialog().createEntity());
        addTransport.setOnAction(event -> new TransportDialog().createEntity());

        rmAmbulance.setOnAction(event -> new AmbulanceDialog().deleteEntity());
        rmTransport.setOnAction(event -> new TransportDialog().deleteEntity());
    }
}