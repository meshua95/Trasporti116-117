package view.dialog;

import digitalTwins.DigitalTwinsBuilder;
import domain.paziente.Autonomia;
import domain.paziente.DatiAnagraficiPaziente;
import domain.paziente.StatoDiSalute;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;

public class PatientDialog {
    public static void createPazienteDialog(){
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

}
