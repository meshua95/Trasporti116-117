package viewcallcenter.dialog;

import digitaltwinsapi.GenerateId;
import digitaltwinsapi.GetPatient;
import digitaltwinsapi.DeleteBookingTransport;
import digitaltwinsapi.CreateBookingTransport;
import digitaltwinsapi.GetBooking;
import digitaltwinsapi.CreateRequest;
import domain.DTDLAddress;
import domain.DTDLCity;
import domain.DTDLHouseNumber;
import domain.DTDLDistrict;
import domain.DTDLPostalCode;
import domain.patient.PatientFiscalCode;
import domain.request.serviceRequest.BookingLocation;
import domain.request.serviceRequest.BookingRoute;
import domain.request.serviceRequest.BookingTransportId;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import utils.errorCode.DeleteDigitalTwinStatusCode;
import utils.errorCode.QueryTimeOutException;
import viewcallcenter.utils.ControllInputField;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ServiceRequestAndBookingDialog extends DtDialog {
    private static final String WITHOUT_BOOKING = "Registra richiesta di servizio senza prenotazione";
    private static final String ADD_BOOKING = "Aggiungi prenotazione";

    private static final ButtonType ADD_BOOKING_BT = new ButtonType(ADD_BOOKING);
    private static final ButtonType WITHOUT_BOOKING_BT = new ButtonType(WITHOUT_BOOKING);
    public static final String ADDRESS = "Via";
    public static final String NUMBER = "Numero";
    public static final String CITY = "Città";
    public static final String DISTRICT = "Provincia";
    public static final String POSTAL_CODE = "Cap";

    private static final Logger LOGGER = Logger.getLogger(ServiceRequestAndBookingDialog.class.toString());

    @Override
    public void createEntity() {
        initialize("Richiesta di servizio", ADD_BOOKING_BT, WITHOUT_BOOKING_BT, ButtonType.CANCEL);
        getDtGridPane().add(new Label("Partenza"), 0, 1);

        TextField departureAddress = new TextField();
        departureAddress.setPromptText(ADDRESS);
        departureAddress.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                ControllInputField.validate(ControllInputField.ADDRESS_PATTERN, departureAddress);
            }
        });
        getDtGridPane().add(new Label(ADDRESS), 0, 2);
        getDtGridPane().add(departureAddress, 1, 2);

        TextField departureNumber = new TextField();
        departureNumber.setPromptText(NUMBER);
        departureNumber.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                ControllInputField.validate(ControllInputField.NUMBER_PATTERN, departureNumber);
            }
        });
        getDtGridPane().add(new Label(NUMBER), 0, 3);
        getDtGridPane().add(departureNumber, 1, 3);

        TextField departureCity = new TextField();
        departureCity.setPromptText(CITY);
        departureCity.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                ControllInputField.validate(ControllInputField.CITY_PATTERN, departureCity);
            }
        });
        getDtGridPane().add(new Label(CITY), 0, 4);
        getDtGridPane().add(departureCity, 1, 4);

        TextField departureDistrict = new TextField();
        departureDistrict.setPromptText(DISTRICT);
        departureDistrict.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                ControllInputField.validate(ControllInputField.DISTRICT_PATTERN, departureDistrict);
            }
        });
        getDtGridPane().add(new Label(DISTRICT), 0, 5);
        getDtGridPane().add(departureDistrict, 1, 5);

        TextField departurePostalCode = new TextField();
        departurePostalCode.setPromptText(POSTAL_CODE);
        departurePostalCode.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                ControllInputField.validate(ControllInputField.POSTALCODE_NUMBER_PATTERN, departurePostalCode);
            }
        });
        getDtGridPane().add(new Label(POSTAL_CODE), 0, 6);
        getDtGridPane().add(departurePostalCode, 1, 6);

        getDtGridPane().add(new Label("Arrivo"), 0, 7);

        TextField destinationAddress = new TextField();
        destinationAddress.setPromptText(ADDRESS);
        destinationAddress.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                ControllInputField.validate(ControllInputField.ADDRESS_PATTERN, destinationAddress);
            }
        });
        getDtGridPane().add(new Label(ADDRESS), 0, 8);
        getDtGridPane().add(destinationAddress, 1, 8);

        TextField destinationNumber = new TextField();
        destinationNumber.setPromptText(NUMBER);
        destinationNumber.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                ControllInputField.validate(ControllInputField.NUMBER_PATTERN, destinationNumber);
            }
        });
        getDtGridPane().add(new Label(NUMBER), 0, 9);
        getDtGridPane().add(destinationNumber, 1, 9);

        TextField destinationCity = new TextField();
        destinationCity.setPromptText(CITY);
        destinationCity.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                ControllInputField.validate(ControllInputField.CITY_PATTERN, destinationCity);
            }
        });
        getDtGridPane().add(new Label(CITY), 0, 10);
        getDtGridPane().add(destinationCity, 1, 10);

        TextField destinationDistrict = new TextField();
        destinationDistrict.setPromptText(DISTRICT);
        destinationDistrict.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                ControllInputField.validate(ControllInputField.DISTRICT_PATTERN, destinationDistrict);
            }
        });
        getDtGridPane().add(new Label(DISTRICT), 0, 11);
        getDtGridPane().add(destinationDistrict, 1, 11);

        TextField destinationPostalCode = new TextField();
        destinationPostalCode.setPromptText(POSTAL_CODE);
        destinationPostalCode.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                ControllInputField.validate(ControllInputField.POSTALCODE_NUMBER_PATTERN, destinationPostalCode);
            }
        });
        getDtGridPane().add(new Label(POSTAL_CODE), 0, 12);
        getDtGridPane().add(destinationPostalCode, 1, 12);

        DatePicker transportDate = new DatePicker();
        transportDate.setEditable(false);
        getDtGridPane().add(new Label("Data trasporto"), 0, 13);
        getDtGridPane().add(transportDate, 1, 13);

        TextField hourTrasporto = new TextField();
        hourTrasporto.setPromptText("hh:mm");
        hourTrasporto.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                ControllInputField.validate(ControllInputField.HOUR_PATTERN, hourTrasporto);
            }
        });
        getDtGridPane().add(new Label("Ora trasporto"), 0, 14);
        getDtGridPane().add(hourTrasporto, 1, 14);

        ComboBox<String> patient = new ComboBox<>();
        try {
            GetPatient.getAllPatientId().forEach(p -> patient.getItems().add(p.getFiscalCode()));
        } catch (QueryTimeOutException e) {
            if (LOGGER.isLoggable(Level.SEVERE)) {
                LOGGER.log(Level.SEVERE, e.toString(), e);
            }
        }
        getDtGridPane().add(new Label("Paziente"), 0, 15);
        getDtGridPane().add(patient, 1, 15);

        getDtDialog().getDialogPane().setContent(getDtGridPane());

        ButtonType buttonType = getDtDialog().showAndWait().get();

        if (ADD_BOOKING_BT.equals(buttonType)) {
            if (ControllInputField.CITY_PATTERN.matcher(departureCity.getText()).matches()
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
                                new BookingLocation(new DTDLAddress(departureAddress.getText()),
                                        new DTDLHouseNumber(Integer.parseInt(departureNumber.getText())),
                                        new DTDLCity(departureCity.getText()),
                                        new DTDLDistrict(departureDistrict.getText()),
                                        new DTDLPostalCode(Integer.parseInt(departurePostalCode.getText()))),
                                new BookingLocation(new DTDLAddress(destinationAddress.getText()),
                                        new DTDLHouseNumber(Integer.parseInt(destinationNumber.getText())),
                                        new DTDLCity(destinationCity.getText()),
                                        new DTDLDistrict(destinationDistrict.getText()),
                                        new DTDLPostalCode(Integer.parseInt(destinationPostalCode.getText())))),
                        new PatientFiscalCode(patient.getValue()),
                        GenerateId.generateServiceRequestId(dateTime)).getId();
                new Alert(Alert.AlertType.INFORMATION, ControllInputField.BOOKING_CONFIRM + id, ButtonType.CLOSE).show();
            } else {
                new Alert(Alert.AlertType.ERROR, ControllInputField.TEXT_FIELD_ERROR, ButtonType.CLOSE).show();
            }
        } else if (WITHOUT_BOOKING_BT.equals(buttonType)) {
            String id = CreateRequest.createServiceRequest(LocalDateTime.now()).getId();
            new Alert(Alert.AlertType.INFORMATION, ControllInputField.SERVICE_REQUEST_CONFIRM + id, ButtonType.CLOSE).show();
        }
    }

    public void deleteBooking() {
        initialize("Elimina prenotazione", ButtonType.OK, ButtonType.CANCEL);

        ComboBox<String> transport = new ComboBox<>();
        try {
            GetBooking.getAllBookingId().forEach(t -> transport.getItems().add(t.getId()));
        } catch (QueryTimeOutException e) {
            if (LOGGER.isLoggable(Level.SEVERE)) {
                LOGGER.log(Level.SEVERE, e.toString(), e);
            }
        }
        getDtGridPane().add(new Label("Prenotazione trasporto"), 0, 0);
        getDtGridPane().add(transport, 1, 0);

        getDtDialog().getDialogPane().setContent(getDtGridPane());

        getDtDialog().showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> {
                    if (DeleteBookingTransport.deleteBookingTransport(new BookingTransportId(transport.getValue())) == DeleteDigitalTwinStatusCode.DELETED) {
                        new Alert(Alert.AlertType.INFORMATION, ControllInputField.BOOKING_DELETED, ButtonType.CLOSE).show();
                    }
                });
    }
}
