package view.dialog;

import digitalTwins.booking.BookingDigitalTwin;
import digitalTwins.patient.PatientDigitalTwin;
import digitalTwins.request.ServiceRequestDigitalTwin;
import domain.*;
import domain.patientBoundedContext.PatientFiscalCode;
import domain.requestBoundedContext.serviceRequest.*;
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
        gridPane.add(new Label("Via"), 0, 2);
        gridPane.add(departureAddress, 1, 2);

        TextField departureNumber = new TextField();
        departureNumber.setPromptText("Numero");
        gridPane.add(new Label("Numero"), 0, 3);
        gridPane.add(departureNumber, 1, 3);

        TextField departureCity = new TextField();
        departureCity.setPromptText("Città");
        gridPane.add(new Label("Città"), 0, 4);
        gridPane.add(departureCity, 1, 4);

        TextField departureDistrict = new TextField();
        departureDistrict.setPromptText("Provincia");
        gridPane.add(new Label("Provincia"), 0, 5);
        gridPane.add(departureDistrict, 1, 5);

        TextField departurePostalCode = new TextField();
        departurePostalCode.setPromptText("Cap");
        gridPane.add(new Label("Cap"), 0, 6);
        gridPane.add(departurePostalCode, 1, 6);

        gridPane.add(new Label("Arrivo"), 0, 7);

        TextField destinationAddress = new TextField();
        destinationAddress.setPromptText("Via");
        gridPane.add(new Label("Via"), 0, 8);
        gridPane.add(destinationAddress, 1, 8);

        TextField destinationNumber = new TextField();
        destinationNumber.setPromptText("Numero");
        gridPane.add(new Label("Numero"), 0, 9);
        gridPane.add(destinationNumber, 1, 9);

        TextField destinationCity = new TextField();
        destinationCity.setPromptText("Città");
        gridPane.add(new Label("Città"), 0, 10);
        gridPane.add(destinationCity, 1, 10);

        TextField destinationDistrict = new TextField();
        destinationDistrict.setPromptText("Provincia");
        gridPane.add(new Label("Provincia"), 0, 11);
        gridPane.add(destinationDistrict, 1, 11);

        TextField destinationPostalCode = new TextField();
        destinationPostalCode.setPromptText("Cap");
        gridPane.add(new Label("Cap"), 0, 12);
        gridPane.add(destinationPostalCode, 1, 12);

        DatePicker transportDate = new DatePicker();
        transportDate.setEditable(false);
        gridPane.add(new Label("Data trasporto"), 0, 13);
        gridPane.add(transportDate, 1, 13);

        TextField hourTrasporto = new TextField();
        hourTrasporto.setPromptText("hh:mm");
        gridPane.add(new Label("Ora trasporto"), 0, 14);
        gridPane.add(hourTrasporto, 1, 14);

        ComboBox<String> patient = new ComboBox<>();
        PatientDigitalTwin.getAllPatientId().forEach(p -> patient.getItems().add(p.getFiscalCode()));
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
                ServiceRequestDigitalTwin.createServiceRequest(LocalDateTime.now());

                //Create Booking Dt
                String id = BookingDigitalTwin.createBookingTransport(LocalDateTime.of(transportDate.getValue().getYear(),
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
                        ServiceRequestDigitalTwin.generateServiceRequestId(dateTime)).getId();
                new Alert(Alert.AlertType.INFORMATION, ControllInputField.BOOKING_CONFIRM + id, ButtonType.CLOSE).show();
            }else{
                new Alert(Alert.AlertType.ERROR, ControllInputField.TEXT_FIELD_ERROR, ButtonType.CLOSE).show();
            }
        } else if (withoutBooking.equals(buttonType)) {
            String id = ServiceRequestDigitalTwin.createServiceRequest(LocalDateTime.now()).getserviceRequestId();
            new Alert(Alert.AlertType.INFORMATION, ControllInputField.SERVICE_REQUEST_CONFIRM + id, ButtonType.CLOSE).show();
        }
    }

    public void deleteBooking() {
        initialize("Elimina prenotazione", ButtonType.OK, ButtonType.CANCEL);

        ComboBox<String> transport = new ComboBox<>();
        BookingDigitalTwin.getAllBookingId().forEach(t -> transport.getItems().add(t.getId()));
        gridPane.add(new Label("Prenotazione trasporto"), 0, 0);
        gridPane.add(transport, 1, 0);

        dialog.getDialogPane().setContent(gridPane);

        dialog.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> {
                    if(BookingDigitalTwin.deleteBookingTransport(new BookingTransportId(transport.getValue())) == DeleteBookingStatusCode.DELETED)
                        new Alert(Alert.AlertType.INFORMATION, ControllInputField.BOOKING_DELETED, ButtonType.CLOSE).show();
                });
    }
}
