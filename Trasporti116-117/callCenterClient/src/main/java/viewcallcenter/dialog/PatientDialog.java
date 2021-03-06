package viewcallcenter.dialog;

import digitaltwinsapi.CreatePatient;

import domain.DTDLAddress;
import domain.DTDLCity;
import domain.DTDLDistrict;
import domain.DTDLHouseNumber;
import domain.DTDLPostalCode;
import domain.patient.Autonomy;
import domain.patient.HealthState;
import domain.patient.PatientFiscalCode;
import domain.patient.PatientResidence;
import domain.patient.PatientPersonalData;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import viewcallcenter.utils.ControllInputField;

import java.time.LocalDate;

public final class PatientDialog extends DtDialog {
    @Override
    public void createEntity() {
        initialize("Aggiungi Paziente", ButtonType.OK, ButtonType.CANCEL);

        TextField name = new TextField();
        name.setPromptText("Nome");
        name.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                ControllInputField.validate(ControllInputField.STRING_PATTERN, name);
            }
        });
        getDtGridPane().add(new Label("Nome"), 0, 0);
        getDtGridPane().add(name, 1, 0);

        TextField surname = new TextField();
        surname.setPromptText("Cognome");
        surname.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                ControllInputField.validate(ControllInputField.STRING_PATTERN, surname);
            }
        });
        getDtGridPane().add(new Label("Cognome"), 0, 1);
        getDtGridPane().add(surname, 1, 1);

        DatePicker birthday = new DatePicker();
        birthday.setEditable(false);
        getDtGridPane().add(new Label("Data di nascita"), 0, 2);
        getDtGridPane().add(birthday, 1, 2);

        TextField address = new TextField();
        address.setPromptText("Via");
        address.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                ControllInputField.validate(ControllInputField.ADDRESS_PATTERN, address);
            }
        });
        getDtGridPane().add(new Label("Via"), 0, 3);
        getDtGridPane().add(address, 1, 3);

        TextField houseNumber = new TextField();
        houseNumber.setPromptText("Numero");
        houseNumber.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                ControllInputField.validate(ControllInputField.NUMBER_PATTERN, houseNumber);
            }
        });
        getDtGridPane().add(new Label("Numero"), 0, 4);
        getDtGridPane().add(houseNumber, 1, 4);

        TextField city = new TextField();
        city.setPromptText("Citt??");
        city.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                ControllInputField.validate(ControllInputField.CITY_PATTERN, city);
            }
        });
        getDtGridPane().add(new Label("Citt??"), 0, 5);
        getDtGridPane().add(city, 1, 5);

        TextField district = new TextField();
        district.setPromptText("Provincia");
        district.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                ControllInputField.validate(ControllInputField.DISTRICT_PATTERN, district);
            }
        });
        getDtGridPane().add(new Label("Provincia"), 0, 6);
        getDtGridPane().add(district, 1, 6);

        TextField postalCode = new TextField();
        postalCode.setPromptText("Cap");
        postalCode.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                ControllInputField.validate(ControllInputField.POSTALCODE_NUMBER_PATTERN, postalCode);
            }
        });
        getDtGridPane().add(new Label("Cap"), 0, 7);
        getDtGridPane().add(postalCode, 1, 7);

        TextField fiscalCode = new TextField();
        fiscalCode.setPromptText("Codice fiscale");
        fiscalCode.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                ControllInputField.validate(ControllInputField.FISCAL_CODE, fiscalCode);
            }
        });
        getDtGridPane().add(new Label("Codice fiscale"), 0, 8);
        getDtGridPane().add(fiscalCode, 1, 8);

        TextField healthState = new TextField();
        healthState.setPromptText("Stato di salute");
        getDtGridPane().add(new Label("Stato di salute"), 0, 9);
        getDtGridPane().add(healthState, 1, 9);

        ComboBox<Autonomy> autonomy = new ComboBox<>();
        autonomy.getItems().addAll(
                Autonomy.AUTONOMOUS,
                Autonomy.NOT_AUTONOMOUS,
                Autonomy.PARTIALLY_AUTONOMOUS
        );
        getDtGridPane().add(new Label("Autonomia"), 0, 10);
        getDtGridPane().add(autonomy, 1, 10);

        getDtDialog().getDialogPane().setContent(getDtGridPane());

        getDtDialog().showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> {
                    if (ControllInputField.STRING_PATTERN.matcher(name.getText()).matches()
                            && ControllInputField.STRING_PATTERN.matcher(surname.getText()).matches()
                            && ControllInputField.CITY_PATTERN.matcher(city.getText()).matches()
                            && ControllInputField.ADDRESS_PATTERN.matcher(address.getText()).matches()
                            && ControllInputField.DISTRICT_PATTERN.matcher(district.getText()).matches()
                            && ControllInputField.POSTALCODE_NUMBER_PATTERN.matcher(postalCode.getText()).matches()
                            && ControllInputField.NUMBER_PATTERN.matcher(houseNumber.getText()).matches()
                            && ControllInputField.FISCAL_CODE.matcher(fiscalCode.getText()).matches()
                    ) {
                        String id = CreatePatient.createPatient(
                                new PatientFiscalCode(fiscalCode.getText()),
                                new PatientPersonalData(
                                        name.getText(),
                                        surname.getText(),
                                        LocalDate.of(birthday.getValue().getYear(), birthday.getValue().getMonth(), birthday.getValue().getDayOfMonth()),
                                        new PatientResidence(
                                                new DTDLAddress(address.getText()),
                                                new DTDLHouseNumber(Integer.parseInt(houseNumber.getText())),
                                                new DTDLCity(city.getText()),
                                                new DTDLDistrict(district.getText()),
                                                new DTDLPostalCode(Integer.parseInt(postalCode.getText())))),
                                new HealthState(healthState.getText()),
                                autonomy.getValue()
                        );
                        new Alert(Alert.AlertType.INFORMATION, ControllInputField.PATIENT_CONFIRM + id, ButtonType.CLOSE).show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, ControllInputField.TEXT_FIELD_ERROR, ButtonType.CLOSE).show();
                    }
                });

    }

}
