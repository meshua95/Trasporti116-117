package view.dialog;

import digitalTwins.operator.OperatorDigitalTwin;
import domain.*;
import domain.transportBoundedContext.*;
import domain.transportBoundedContext.OperatorId;
import javafx.scene.control.*;
import view.utils.ControllInputField;

import java.time.LocalDate;

public class OperatorDialog extends DtDialog {

    @Override
    public void createEntity(){
        initialize("Inserisci operatore", ButtonType.OK, ButtonType.CANCEL);
        TextField name = new TextField();
        name.setPromptText("Nome");
        gridPane.add(new Label("Nome"), 0, 0);
        gridPane.add(name, 1, 0);

        TextField surname = new TextField();
        surname.setPromptText("Cognome");
        gridPane.add(new Label("Cognome"), 0, 1);
        gridPane.add(surname, 1, 1);

        DatePicker birthday = new DatePicker();
        birthday.setEditable(false);
        gridPane.add(new Label("Data di nascita"), 0, 2);
        gridPane.add(birthday, 1, 2);

        TextField address = new TextField();
        address.setPromptText("Via");
        gridPane.add(new Label("Via"), 0, 3);
        gridPane.add(address, 1, 3);

        TextField houseNumber = new TextField();
        houseNumber.setPromptText("Numero");
        gridPane.add(new Label("Numero"), 0, 4);
        gridPane.add(houseNumber, 1, 4);

        TextField city = new TextField();
        city.setPromptText("Città");
        gridPane.add(new Label("Città"), 0, 5);
        gridPane.add(city, 1, 5);

        TextField distric = new TextField();
        distric.setPromptText("Provincia");
        gridPane.add(new Label("Provincia"), 0, 6);
        gridPane.add(distric, 1, 6);

        TextField postalCode = new TextField();
        postalCode.setPromptText("Cap");
        gridPane.add(new Label("Cap"), 0, 7);
        gridPane.add(postalCode, 1, 7);

        TextField fiscalCode = new TextField();
        fiscalCode.setPromptText("Codice fiscale");
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
                            && ControllInputField.DISTRICT_PATTERN.matcher(distric.getText()).matches()
                            && ControllInputField.POSTALCODE_NUMBER_PATTERN.matcher(postalCode.getText()).matches()
                            && ControllInputField.NUMBER_PATTERN.matcher(houseNumber.getText()).matches()
                    ){
                        String id = OperatorDigitalTwin.createOperator(
                                new OperatorId(fiscalCode.getText()),
                                new OperatorPersonalData(
                                        name.getText(),
                                        surname.getText(),
                                        LocalDate.of(birthday.getValue().getYear(), birthday.getValue().getMonth(),birthday.getValue().getDayOfMonth()),
                                        new OperatorResidence(
                                                new Address(address.getText()),
                                                new HouseNumber(Integer.parseInt(houseNumber.getText())),
                                                new City(city.getText()),
                                                new District(distric.getText()),
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
