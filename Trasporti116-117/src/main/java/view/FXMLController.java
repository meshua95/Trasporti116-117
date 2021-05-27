/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package view;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import domain.ambulanza.AmbulanzaDigitalTwin;
import model.*;
import domain.operatore.OperatoreDigitalTwin;
import domain.paziente.PazienteDigitalTwin;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

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
        addAmbulanza.setOnAction(event -> createAmbulanzaDialog());
        addPaziente.setOnAction(event -> createPazienteDialog());
        addOperatore.setOnAction(event -> createOperatoreDialog());
        addTrasporto.setOnAction(event -> createTrasportoDialog());
    }

    private void createTrasportoDialog(){

    }

    private void createAmbulanzaDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Aggiungi ambulanza");

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        TextField numAmbulanza = new TextField();
        numAmbulanza.setPromptText("Numero ambulanza");

        gridPane.add(new Label("Numero ambulanza"), 0, 0);
        gridPane.add(numAmbulanza, 1, 0);

        dialog.getDialogPane().setContent(gridPane);

        dialog.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> AmbulanzaDigitalTwin.createAmbulanza(StatoAmbulanza.PRONTA, Integer.parseInt(numAmbulanza.getText())));

    }

    private void createPazienteDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Inserisci paziente");

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        TextField nome = new TextField();
        nome.setPromptText("Nome");
        gridPane.add(new Label("Nome"), 0, 0);
        gridPane.add(nome, 1, 0);

        TextField cognome = new TextField();
        cognome.setPromptText("Cognome");
        gridPane.add(new Label("Cognome"), 0, 1);
        gridPane.add(cognome, 1, 1);

        DatePicker dataNascita = new DatePicker();
        gridPane.add(new Label("Data di nascita"), 0, 2);
        gridPane.add(dataNascita, 1, 2);

        TextField via = new TextField();
        via.setPromptText("Via");
        gridPane.add(new Label("Via"), 0, 3);
        gridPane.add(via, 1, 3);

        TextField numero = new TextField();
        numero.setPromptText("Numero");
        gridPane.add(new Label("Numero"), 0, 4);
        gridPane.add(numero, 1, 4);

        TextField città = new TextField();
        città.setPromptText("Città");
        gridPane.add(new Label("Città"), 0, 5);
        gridPane.add(città, 1, 5);

        TextField provincia = new TextField();
        provincia.setPromptText("Provincia");
        gridPane.add(new Label("Provincia"), 0, 6);
        gridPane.add(provincia, 1, 6);

        TextField cap = new TextField();
        cap.setPromptText("Cap");
        gridPane.add(new Label("Cap"), 0, 7);
        gridPane.add(cap, 1, 7);

        TextField cf = new TextField();
        cf.setPromptText("Codice fiscale");
        gridPane.add(new Label("Codice fiscale"), 0, 8);
        gridPane.add(cf, 1, 8);
        TextField statoSalute = new TextField();

        statoSalute.setPromptText("Stato di salute");
        gridPane.add(new Label("Stato di salute"), 0, 9);
        gridPane.add(statoSalute, 1, 9);

        ComboBox<Autonomy> autonomy = new ComboBox<>();
        autonomy.getItems().addAll(
                Autonomy.AUTONOMOUS,
                Autonomy.PARTIALLY_AUTONOMOUS,
                Autonomy.NOT_AUTONOMOUS
        );
        gridPane.add(new Label("Autonomia"), 0, 10);
        gridPane.add(autonomy, 1, 10);

        dialog.getDialogPane().setContent(gridPane);

        dialog.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> PazienteDigitalTwin.createPaziente(
                        cf.getText(),
                        new PersonalData(
                                nome.getText(),
                                cognome.getText(),
                                LocalDate.of(dataNascita.getValue().getYear(), dataNascita.getValue().getMonth(),dataNascita.getValue().getDayOfMonth()),
                                new Location(
                                        new Address(via.getText()),
                                        new HouseNumber(numero.getText()),
                                        new City(città.getText()),
                                        new District(provincia.getText()),
                                        new PostalCode(cap.getText()))),
                        new StatoDiSalute(statoSalute.getText()),
                        autonomy.getValue()
                ));

    }

    private void createOperatoreDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Inserisci operatore");

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        TextField nome = new TextField();
        nome.setPromptText("Nome");
        gridPane.add(new Label("Nome"), 0, 0);
        gridPane.add(nome, 1, 0);

        TextField cognome = new TextField();
        cognome.setPromptText("Cognome");
        gridPane.add(new Label("Cognome"), 0, 1);
        gridPane.add(cognome, 1, 1);

        DatePicker dataNascita = new DatePicker();
        gridPane.add(new Label("Data di nascita"), 0, 2);
        gridPane.add(dataNascita, 1, 2);

        TextField via = new TextField();
        via.setPromptText("Via");
        gridPane.add(new Label("Via"), 0, 3);
        gridPane.add(via, 1, 3);

        TextField numero = new TextField();
        numero.setPromptText("Numero");
        gridPane.add(new Label("Numero"), 0, 4);
        gridPane.add(numero, 1, 4);

        TextField città = new TextField();
        città.setPromptText("Città");
        gridPane.add(new Label("Città"), 0, 5);
        gridPane.add(città, 1, 5);

        TextField provincia = new TextField();
        provincia.setPromptText("Provincia");
        gridPane.add(new Label("Provincia"), 0, 6);
        gridPane.add(provincia, 1, 6);

        TextField cap = new TextField();
        cap.setPromptText("Cap");
        gridPane.add(new Label("Cap"), 0, 7);
        gridPane.add(cap, 1, 7);

        TextField cf = new TextField();
        cf.setPromptText("Codice fiscale");
        gridPane.add(new Label("Codice fiscale"), 0, 8);
        gridPane.add(cf, 1, 8);
        TextField statoSalute = new TextField();

        dialog.getDialogPane().setContent(gridPane);

        dialog.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> OperatoreDigitalTwin.createOperatore(
                        cf.getText(),
                        new PersonalData(
                                nome.getText(),
                                cognome.getText(),
                                LocalDate.of(dataNascita.getValue().getYear(), dataNascita.getValue().getMonth(),dataNascita.getValue().getDayOfMonth()),
                                new Location(
                                        new Address(via.getText()),
                                        new HouseNumber(numero.getText()),
                                        new City(città.getText()),
                                        new District(provincia.getText()),
                                        new PostalCode(cap.getText()))
                        )
                ));

    }
}