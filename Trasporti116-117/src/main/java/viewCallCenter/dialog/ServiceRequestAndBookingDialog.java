package viewCallCenter.dialog;

import digitalTwinsAPI.GenerateId;
import digitalTwinsAPI.GetPatient;
import digitalTwinsAPI.DeleteBookingTransport;
import digitalTwinsAPI.CreateBookingTransport;
import digitalTwinsAPI.GetBooking;
import digitalTwinsAPI.CreateRequest;
import domain.*;
import domain.patient.PatientFiscalCode;
import domain.request.serviceRequest.*;
import javafx.scene.control.*;
import utils.errorCode.DeleteBookingStatusCode;
import view.utils.ControllInputField;

import java.time.LocalDateTime;

public class ServiceRequestAndBookingDialog extends DtDialog{
    private static final String WITHOUT_BOOKING = "Registra richiesta di servizio senza prenotazione";
    private static final String ADD_BOOKING = "Aggiungi prenotazione";

    private ButtonType addBooking = new ButtonType(ADD_BOOKING);
    private ButtonType withoutBooking = new ButtonType(WITHOUT_BOOKING);

    @Override
    public void createEntity(){
        initialize("Richiesta di servizio", addBooking, withoutBooking, ButtonType.CANCEL);
        gridPane.add(new Label("Partenza"), 0, 1);

        TextField departureAddress = new TextField();
        departureAddress.setPromptText("Via");
        departureAddress.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                ControllInputField.validate(ControllInputField.ADDRESS_PATTERN, departureAddress);
            }
        });
        gridPane.add(new Label("Via"), 0, 2);
        gridPane.add(departureAddress, 1, 2);

        TextField departureNumber = new TextField();
        departureNumber.setPromptText("Numero");
        departureNumber.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                ControllInputField.validate(ControllInputField.NUMBER_PATTERN, departureNumber);
            }
        });
        gridPane.add(new Label("Numero"), 0, 3);
        gridPane.add(departureNumber, 1, 3);

        TextField departureCity = new TextField();
        departureCity.setPromptText("Città");
        departureCity.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                ControllInputField.validate(ControllInputField.CITY_PATTERN, departureCity);
            }
        });
        gridPane.add(new Label("Città"), 0, 4);
        gridPane.add(departureCity, 1, 4);

        TextField departureDistrict = new TextField();
        departureDistrict.setPromptText("Provincia");
        departureDistrict.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                ControllInputField.validate(ControllInputField.DISTRICT_PATTERN, departureDistrict);
            }
        });
        gridPane.add(new Label("Provincia"), 0, 5);
        gridPane.add(departureDistrict, 1, 5);

        TextField departurePostalCode = new TextField();
        departurePostalCode.setPromptText("Cap");
        departurePostalCode.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                ControllInputField.validate(ControllInputField.POSTALCODE_NUMBER_PATTERN, departurePostalCode);
            }
        });
        gridPane.add(new Label("Cap"), 0, 6);
        gridPane.add(departurePostalCode, 1, 6);

        gridPane.add(new Label("Arrivo"), 0, 7);

        TextField destinationAddress = new TextField();
        destinationAddress.setPromptText("Via");
        destinationAddress.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                ControllInputField.validate(ControllInputField.ADDRESS_PATTERN, destinationAddress);
            }
        });
        gridPane.add(new Label("Via"), 0, 8);
        gridPane.add(destinationAddress, 1, 8);

        TextField destinationNumber = new TextField();
        destinationNumber.setPromptText("Numero");
        destinationNumber.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                ControllInputField.validate(ControllInputField.NUMBER_PATTERN, destinationNumber);
            }
        });
        gridPane.add(new Label("Numero"), 0, 9);
        gridPane.add(destinationNumber, 1, 9);

        TextField destinationCity = new TextField();
        destinationCity.setPromptText("Città");
        destinationCity.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                ControllInputField.validate(ControllInputField.CITY_PATTERN, destinationCity);
            }
        });
        gridPane.add(new Label("Città"), 0, 10);
        gridPane.add(destinationCity, 1, 10);

        TextField destinationDistrict = new TextField();
        destinationDistrict.setPromptText("Provincia");
        destinationDistrict.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                ControllInputField.validate(ControllInputField.DISTRICT_PATTERN, destinationDistrict);
            }
        });
        gridPane.add(new Label("Provincia"), 0, 11);
        gridPane.add(destinationDistrict, 1, 11);

        TextField destinationPostalCode = new TextField();
        destinationPostalCode.setPromptText("Cap");
        destinationPostalCode.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                ControllInputField.validate(ControllInputField.POSTALCODE_NUMBER_PATTERN, destinationPostalCode);
            }
        });
        gridPane.add(new Label("Cap"), 0, 12);
        gridPane.add(destinationPostalCode, 1, 12);

        DatePicker transportDate = new DatePicker();
        transportDate.setEditable(false);
        gridPane.add(new Label("Data trasporto"), 0, 13);
        gridPane.add(transportDate, 1, 13);

        TextField hourTrasporto = new TextField();
        hourTrasporto.setPromptText("hh:mm");
        hourTrasporto.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                ControllInputField.validate(ControllInputField.HOUR_PATTERN, hourTrasporto);
            }
        });
        gridPane.add(new Label("Ora trasporto"), 0, 14);
        gridPane.add(hourTrasporto, 1, 14);

        ComboBox<String> patient = new ComboBox<>();
        GetPatient.getAllPatientId().forEach(p -> patient.getItems().add(p.getFiscalCode()));
        gridPane.add(new Label("Paziente"), 0, 15);
        gridPane.add(patient, 1, 15);

        dialog.getDialogPane().setContent(gridPane);

        ButtonType buttonType = dialog.showAndWait().get();

        if (addBooking.equals(buttonType)) {
            if(ControllInputField.CITY_PATTERN.matcher(departureCity.getText()).matches()
                    && ControllInputField.ADDRESS_PATTERN.matcher(departureAddress.getText()).matches()
                    && ControllInputField.DISTRICT_PATTERN.matcher(departureDistrict.getText()).matches()
                    && ControllInputField.POSTALCODE_NUMBER_PATTERN.matcher(departurePostalCode.getText()).matches()
                    && ControllInputField.NUMBER_PATTERN.matcher(departureNumber.getText()).matches()
                    && ControllInputField.CITY_PATTERN.matcher(destinationCity.getText()).matches()
                    && ControllInputField.ADDRESS_PATTERN.matcher(destinationAddress.getText()).matches()
                    && ControllInputField.DISTRICT_PATTERN.matcher(destinationDistrict.getText()).matches()
                    && ControllInputField.POSTALCODE_NUMBER_PATTERN.matcher(destinationPostalCode.getText()).matches()
                    && ControllInputField.NUMBER_PATTERN.matcher(destinationNumber.getText()).matches()
                    && ControllInputField.HOUR_PATTERN.matcher(hourTrasporto.getText()).matches()
            ) {
                LocalDateTime dateTime = LocalDateTime.now();

                //Create Service Request DT
                CreateRequest.createServiceRequest(LocalDateTime.now());

                //Create Booking Dt
                String id = CreateBookingTransport.createBookingTransport(LocalDateTime.of(transportDate.getValue().getYear(),
                        transportDate.getValue().getMonth(),
                        transportDate.getValue().getDayOfMonth(),
                        Integer.parseInt(hourTrasporto.getText().split(":")[0]),
                        Integer.parseInt(hourTrasporto.getText().split(":")[1])),
                        new BookingRoute(
                                new BookingLocation(new Address(departureAddress.getText()),
                                        new HouseNumber(Integer.parseInt(departureNumber.getText())),
                                        new City(departureCity.getText()),
                                        new District(departureDistrict.getText()),
                                        new PostalCode(Integer.parseInt(departurePostalCode.getText()))),
                                new BookingLocation(new Address(destinationAddress.getText()),
                                        new HouseNumber(Integer.parseInt(destinationNumber.getText())),
                                        new City(destinationCity.getText()),
                                        new District(destinationDistrict.getText()),
                                        new PostalCode(Integer.parseInt(destinationPostalCode.getText())))),
                        new PatientFiscalCode(patient.getValue()),
                        GenerateId.generateServiceRequestId(dateTime)).getId();
                new Alert(Alert.AlertType.INFORMATION, ControllInputField.BOOKING_CONFIRM + id, ButtonType.CLOSE).show();
            }else{
                new Alert(Alert.AlertType.ERROR, ControllInputField.TEXT_FIELD_ERROR, ButtonType.CLOSE).show();
            }
        } else if (withoutBooking.equals(buttonType)) {
            String id = CreateRequest.createServiceRequest(LocalDateTime.now()).getserviceRequestId();
            new Alert(Alert.AlertType.INFORMATION, ControllInputField.SERVICE_REQUEST_CONFIRM + id, ButtonType.CLOSE).show();
        }
    }

    public void deleteBooking() {
        initialize("Elimina prenotazione", ButtonType.OK, ButtonType.CANCEL);

        ComboBox<String> transport = new ComboBox<>();
        GetBooking.getAllBookingId().forEach(t -> transport.getItems().add(t.getId()));
        gridPane.add(new Label("Prenotazione trasporto"), 0, 0);
        gridPane.add(transport, 1, 0);

        dialog.getDialogPane().setContent(gridPane);

        dialog.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> {
                    if(DeleteBookingTransport.deleteBookingTransport(new BookingTransportId(transport.getValue())) == DeleteBookingStatusCode.DELETED)
                        new Alert(Alert.AlertType.INFORMATION, ControllInputField.BOOKING_DELETED, ButtonType.CLOSE).show();
                });
    }
}
