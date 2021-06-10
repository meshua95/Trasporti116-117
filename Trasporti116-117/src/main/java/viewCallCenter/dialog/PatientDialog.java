package viewCallCenter.dialog;

import digitalTwins.patient.PatientDigitalTwin;
import domain.*;
import domain.patientBoundedContext.*;
import javafx.scene.control.*;
import java.time.LocalDate;

public class PatientDialog extends DtDialog{
    @Override
    public void createEntity(){
        initialize("Aggiungi Paziente", ButtonType.OK, ButtonType.CANCEL);

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

        TextField city = new TextField();
        city.setPromptText("Città");
        gridPane.add(new Label("Città"), 0, 5);
        gridPane.add(city, 1, 5);

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

        ComboBox<Autonomy> autonomia = new ComboBox<>();
        autonomia.getItems().addAll(
                Autonomy.AUTONOMOUS,
                Autonomy.NOT_AUTONOMOUS,
                Autonomy.PARTIALLY_AUTONOMOUS
        );
        gridPane.add(new Label("Autonomia"), 0, 10);
        gridPane.add(autonomia, 1, 10);

        dialog.getDialogPane().setContent(gridPane);

        dialog.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> PatientDigitalTwin.createPatient(
                        new PatientFiscalCode(cf.getText()),
                        new PatientPersonalData(
                                nome.getText(),
                                cognome.getText(),
                                LocalDate.of(dataNascita.getValue().getYear(), dataNascita.getValue().getMonth(),dataNascita.getValue().getDayOfMonth()),
                                new PatientResidence(
                                        new Address(via.getText()),
                                        new HouseNumber(numero.getText()),
                                        new City(city.getText()),
                                        new District(provincia.getText()),
                                        new PostalCode(Integer.parseInt(cap.getText())))),
                        new HealthState(statoSalute.getText()),
                        autonomia.getValue()
                ));

    }

}
