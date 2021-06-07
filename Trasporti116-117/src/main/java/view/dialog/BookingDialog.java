package view.dialog;

import digitalTwins.booking.BookingDigitalTwin;
import digitalTwins.patient.PatientDigitalTwin;
import domain.patientBoundedContext.PatientFiscalCode;
import domain.requestBoundedContext.serviceRequest.*;
import javafx.scene.control.*;
import java.time.LocalDateTime;

public class BookingDialog extends DtDialog{

    @Override
    public void createEntity(){
        initialize("Aggiungi Prenotazione Trasporto");
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
        gridPane.add(new Label("Data trasporto"), 0, 13);
        gridPane.add(transportDate, 1, 13);

        TextField hourTrasporto = new TextField();
        TextField minTrasporto = new TextField();
        hourTrasporto.setPromptText("h");
        minTrasporto.setPromptText("m");
        gridPane.add(new Label("Ora trasporto"), 0, 14);
        gridPane.add(hourTrasporto, 1, 14);
        gridPane.add(minTrasporto, 2, 14);

        ComboBox<String> patient = new ComboBox<>();
        PatientDigitalTwin.getAllPatientId().forEach(p -> patient.getItems().add(p.getFiscalCode()));
        gridPane.add(new Label("Paziente"), 0, 15);
        gridPane.add(patient, 1, 15);

        dialog.getDialogPane().setContent(gridPane);

        dialog.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> BookingDigitalTwin.createBookingTransport(LocalDateTime.of(transportDate.getValue().getYear(),
                                transportDate.getValue().getMonth(),
                                transportDate.getValue().getDayOfMonth(),
                                Integer.parseInt(hourTrasporto.getText()),
                                Integer.parseInt(minTrasporto.getText())),
                        new Route(
                                new BookingLocation(new BookingAddress(departureAddress.getText()),
                                        new BookingHouseNumber(departureNumber.getText()),
                                        new BookingCity(departureCity.getText()),
                                        new BookingDistrict(departureDistrict.getText()),
                                        new BookingPostalCode(Integer.parseInt(departurePostalCode.getText()))),
                                new BookingLocation(new BookingAddress(destinationAddress.getText()),
                                        new BookingHouseNumber(destinationNumber.getText()),
                                        new BookingCity(destinationCity.getText()),
                                        new BookingDistrict(destinationDistrict.getText()),
                                        new BookingPostalCode(Integer.parseInt(destinationPostalCode.getText())))),
                        new PatientFiscalCode(patient.getValue())));

    }

    public void deleteEntity() {
        initialize("Elimina Trasporto");

        ComboBox<String> transport = new ComboBox<>();
        BookingDigitalTwin.getAllBookingId().forEach(t -> transport.getItems().add(t.getId()));
        gridPane.add(new Label("Prenotazione trasporto"), 0, 0);
        gridPane.add(transport, 1, 0);

        dialog.getDialogPane().setContent(gridPane);

        dialog.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> BookingDigitalTwin.deleteBookingTransport(new BookingTransportId(transport.getValue())));
    }
}
