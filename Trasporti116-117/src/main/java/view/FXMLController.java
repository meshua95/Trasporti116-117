/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package view;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import digitalTwins.DigitalTwinQuery;
import digitalTwins.DigitalTwinsBuilder;
import domain.ambulanza.StatoAmbulanza;
import domain.paziente.Autonomia;
import domain.paziente.DatiAnagraficiPaziente;
import domain.paziente.IdPaziente;
import domain.paziente.StatoDiSalute;
import domain.operatore.DatiAnagraficiOperatore;
import domain.trasporto.Itinerario;
import domain.trasporto.StatoTrasporto;
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
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Aggiungi Trasporto");

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        gridPane.add(new Label("Partenza"), 0, 1);

        TextField viaPartenza = new TextField();
        viaPartenza.setPromptText("Via");
        gridPane.add(new Label("Via"), 0, 2);
        gridPane.add(viaPartenza, 1, 2);

        TextField numeroPartenza = new TextField();
        numeroPartenza.setPromptText("Numero");
        gridPane.add(new Label("Numero"), 0, 3);
        gridPane.add(numeroPartenza, 1, 3);

        TextField cittàPartenza = new TextField();
        cittàPartenza.setPromptText("Città");
        gridPane.add(new Label("Città"), 0, 4);
        gridPane.add(cittàPartenza, 1, 4);

        TextField provinciaPartenza = new TextField();
        provinciaPartenza.setPromptText("Provincia");
        gridPane.add(new Label("Provincia"), 0, 5);
        gridPane.add(provinciaPartenza, 1, 5);

        TextField capPartenza = new TextField();
        capPartenza.setPromptText("Cap");
        gridPane.add(new Label("Cap"), 0, 6);
        gridPane.add(capPartenza, 1, 6);

        gridPane.add(new Label("Arrivo"), 0, 7);

        TextField viaArrivo = new TextField();
        viaArrivo.setPromptText("Via");
        gridPane.add(new Label("Via"), 0, 8);
        gridPane.add(viaArrivo, 1, 8);

        TextField numeroArrivo = new TextField();
        numeroArrivo.setPromptText("Numero");
        gridPane.add(new Label("Numero"), 0, 9);
        gridPane.add(numeroArrivo, 1, 9);

        TextField cittàArrivo = new TextField();
        cittàArrivo.setPromptText("Città");
        gridPane.add(new Label("Città"), 0, 10);
        gridPane.add(cittàArrivo, 1, 10);

        TextField provinciaArrivo = new TextField();
        provinciaArrivo.setPromptText("Provincia");
        gridPane.add(new Label("Provincia"), 0, 11);
        gridPane.add(provinciaArrivo, 1, 11);

        TextField capArrivo = new TextField();
        capArrivo.setPromptText("Cap");
        gridPane.add(new Label("Cap"), 0, 12);
        gridPane.add(capArrivo, 1, 12);

        DatePicker dataTrasporto = new DatePicker();
        gridPane.add(new Label("Data trasporto"), 0, 13);
        gridPane.add(dataTrasporto, 1, 13);

        TextField hourTrasporto = new TextField();
        TextField minTrasporto = new TextField();
        hourTrasporto.setPromptText("h");
        minTrasporto.setPromptText("m");
        gridPane.add(new Label("Ora trasporto"), 0, 14);
        gridPane.add(hourTrasporto, 1, 14);
        gridPane.add(minTrasporto, 2, 14);

        ComboBox<String> paziente = new ComboBox<>();
        DigitalTwinQuery.getAllPazienteIdTwins().forEach(p -> paziente.getItems().add(p.getCodiceFiscale()));
        gridPane.add(new Label("Paziente"), 0, 15);
        gridPane.add(paziente, 1, 15);

        ComboBox<String> operatore = new ComboBox<>();
        DigitalTwinQuery.getAllOperatoreIdTwins().forEach(o -> operatore.getItems().add(o.getCodiceFiscale()));
        gridPane.add(new Label("Operatore"), 0, 16);
        gridPane.add(operatore, 1, 16);

        ComboBox<String> ambulanza = new ComboBox<>();
        DigitalTwinQuery.getAllAmbulanzaIdTwins().forEach(a -> ambulanza.getItems().add(a.getIdAmbulanza()));
        gridPane.add(new Label("Ambulanza"), 0, 17);
        gridPane.add(ambulanza, 1, 17);

        dialog.getDialogPane().setContent(gridPane);

        dialog.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> DigitalTwinsBuilder.createTrasportoDigitalTwin("Trasporto2",
                        LocalDateTime.of(dataTrasporto.getValue().getYear(),
                                dataTrasporto.getValue().getMonth(),
                                dataTrasporto.getValue().getDayOfMonth(),
                                Integer.parseInt(hourTrasporto.getText()),
                                Integer.parseInt(minTrasporto.getText())),
                        StatoTrasporto.NON_INIZIATO,
                        new Itinerario(
                                new Itinerario.Luogo(viaPartenza.getText(),
                                        numeroPartenza.getText(),
                                        cittàPartenza.getText(),
                                        provinciaPartenza.getText(),
                                        Integer.parseInt(capPartenza.getText())),
                                new Itinerario.Luogo(viaArrivo.getText(),
                                        numeroArrivo.getText(),
                                        cittàArrivo.getText(),
                                        provinciaArrivo.getText(),
                                        Integer.parseInt(capArrivo.getText()))),
                        ambulanza.getValue(),
                        paziente.getValue(),
                        operatore.getValue()));

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
                .ifPresent(response -> DigitalTwinsBuilder.createAmbulanzaDigitalTwin(StatoAmbulanza.PRONTA, Integer.parseInt(numAmbulanza.getText())));

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

        ComboBox<Autonomia> autonomia = new ComboBox<>();
        autonomia.getItems().addAll(
                Autonomia.AUTONOMO,
                Autonomia.PARZIALMENTE_AUTONOMO,
                Autonomia.NON_AUTONOMO
        );
        gridPane.add(new Label("Autonomia"), 0, 10);
        gridPane.add(autonomia, 1, 10);

        dialog.getDialogPane().setContent(gridPane);

        dialog.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> DigitalTwinsBuilder.createPazienteDigitalTwin(
                        cf.getText(),
                        new DatiAnagraficiPaziente(
                                nome.getText(),
                                cognome.getText(),
                                LocalDate.of(dataNascita.getValue().getYear(), dataNascita.getValue().getMonth(),dataNascita.getValue().getDayOfMonth()),
                                new DatiAnagraficiPaziente.Residenza(via.getText(),numero.getText(),città.getText(), provincia.getText(), Integer.parseInt(cap.getText()))),
                        new StatoDiSalute(statoSalute.getText()),
                        autonomia.getValue()
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
                .ifPresent(response -> DigitalTwinsBuilder.createOperatoreAmbulanzaDigitalTwin(
                        cf.getText(),
                        new DatiAnagraficiOperatore(
                                nome.getText(),
                                cognome.getText(),
                                LocalDate.of(dataNascita.getValue().getYear(), dataNascita.getValue().getMonth(),dataNascita.getValue().getDayOfMonth()),
                                new DatiAnagraficiOperatore.Residenza(via.getText(),numero.getText(),città.getText(), provincia.getText(), Integer.parseInt(cap.getText()))
                        )
                ));

    }
}