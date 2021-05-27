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
    private Button addPaziente;
    @FXML
    private Button addOperatore;
    @FXML
    private Button addAmbulanza;
    @FXML
    private Button addTrasporto;
    @FXML
    private Button rmPaziente;
    @FXML
    private Button rmOperatore;
    @FXML
    private Button rmAmbulanza;
    @FXML
    private Button rmTrasporto;
    @FXML
    private Button trackAmbulanza;
    @FXML
    private Button viewTrasporti;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        addAmbulanza.setOnAction(event -> new AmbulanceDialog().createEntity());
        addPaziente.setOnAction(event -> new PatientDialog().createEntity());
        addOperatore.setOnAction(event -> new OperatorDialog().createEntity());
        addTrasporto.setOnAction(event -> new TransportDialog().createEntity());

        rmAmbulanza.setOnAction(event -> new AmbulanceDialog().deleteEntity());
        rmPaziente.setOnAction(event -> new PatientDialog().deleteEntity());
        rmOperatore.setOnAction(event -> new OperatorDialog().deleteEntity());
        rmTrasporto.setOnAction(event -> new TransportDialog().deleteEntity());
    }
}