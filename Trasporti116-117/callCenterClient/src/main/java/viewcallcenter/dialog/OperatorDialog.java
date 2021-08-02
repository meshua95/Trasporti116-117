package viewcallcenter.dialog;

import digitaltwinsapi.CreateOperator;
import domain.PostalCode;
import domain.Address;
import domain.City;
import domain.District;
import domain.HouseNumber;
import domain.transport.operator.OperatorId;
import domain.transport.operator.OperatorPersonalData;
import domain.transport.operator.OperatorResidence;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import viewcallcenter.utils.ControllInputField;

import java.time.LocalDate;

public class OperatorDialog extends DtDialog {

    @Override
    public void createEntity(){
        initialize("Inserisci operatore", ButtonType.OK, ButtonType.CANCEL);

        TextField name = new TextField();
        name.setPromptText("Nome");
        name.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                ControllInputField.validate(ControllInputField.STRING_PATTERN, name);
            }
        });
        gridPane.add(new Label("Nome"), 0, 0);
        gridPane.add(name, 1, 0);

        TextField surname = new TextField();
        surname.setPromptText("Cognome");
        surname.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                ControllInputField.validate(ControllInputField.STRING_PATTERN, surname);
            }
        });
        gridPane.add(new Label("Cognome"), 0, 1);
        gridPane.add(surname, 1, 1);

        DatePicker birthday = new DatePicker();
        birthday.setEditable(false);
        gridPane.add(new Label("Data di nascita"), 0, 2);
        gridPane.add(birthday, 1, 2);

        TextField address = new TextField();
        address.setPromptText("Via");
        address.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                ControllInputField.validate(ControllInputField.ADDRESS_PATTERN, address);
            }
        });
        gridPane.add(new Label("Via"), 0, 3);
        gridPane.add(address, 1, 3);

        TextField houseNumber = new TextField();
        houseNumber.setPromptText("Numero");
        houseNumber.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                ControllInputField.validate(ControllInputField.NUMBER_PATTERN, houseNumber);
            }
        });
        gridPane.add(new Label("Numero"), 0, 4);
        gridPane.add(houseNumber, 1, 4);

        TextField city = new TextField();
        city.setPromptText("Città");
        city.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                ControllInputField.validate(ControllInputField.CITY_PATTERN, city);
            }
        });
        gridPane.add(new Label("Città"), 0, 5);
        gridPane.add(city, 1, 5);

        TextField district = new TextField();
        district.setPromptText("Provincia");
        district.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                ControllInputField.validate(ControllInputField.DISTRICT_PATTERN, district);
            }
        });
        gridPane.add(new Label("Provincia"), 0, 6);
        gridPane.add(district, 1, 6);

        TextField postalCode = new TextField();
        postalCode.setPromptText("Cap");
        postalCode.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                ControllInputField.validate(ControllInputField.POSTALCODE_NUMBER_PATTERN, postalCode);
            }
        });
        gridPane.add(new Label("Cap"), 0, 7);
        gridPane.add(postalCode, 1, 7);

        TextField fiscalCode = new TextField();
        fiscalCode.setPromptText("Codice fiscale");
        fiscalCode.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                ControllInputField.validate(ControllInputField.FISCAL_CODE, fiscalCode);
            }
        });
        gridPane.add(new Label("Codice fiscale"), 0, 8);
        gridPane.add(fiscalCode, 1, 8);

        dialog.getDialogPane().setContent(gridPane);

        dialog.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> {
                    if(ControllInputField.STRING_PATTERN.matcher(name.getText()).matches()
                            && ControllInputField.STRING_PATTERN.matcher(surname.getText()).matches()
                            && ControllInputField.CITY_PATTERN.matcher(city.getText()).matches()
                            && ControllInputField.ADDRESS_PATTERN.matcher(address.getText()).matches()
                            && ControllInputField.DISTRICT_PATTERN.matcher(district.getText()).matches()
                            && ControllInputField.POSTALCODE_NUMBER_PATTERN.matcher(postalCode.getText()).matches()
                            && ControllInputField.NUMBER_PATTERN.matcher(houseNumber.getText()).matches()
                    ){
                        String id = CreateOperator.createOperator(
                                new OperatorId(fiscalCode.getText()),
                                new OperatorPersonalData(
                                        name.getText(),
                                        surname.getText(),
                                        LocalDate.of(birthday.getValue().getYear(), birthday.getValue().getMonth(),birthday.getValue().getDayOfMonth()),
                                        new OperatorResidence(
                                                new Address(address.getText()),
                                                new HouseNumber(Integer.parseInt(houseNumber.getText())),
                                                new City(city.getText()),
                                                new District(district.getText()),
                                                new PostalCode(Integer.parseInt(postalCode.getText())))
                                )
                        );
                        new Alert(Alert.AlertType.INFORMATION, ControllInputField.OPERATOR_CONFIRM + id, ButtonType.CLOSE).show();
                    }else{
                        new Alert(Alert.AlertType.ERROR, ControllInputField.TEXT_FIELD_ERROR, ButtonType.CLOSE).show();
                    }
                });

    }

}
