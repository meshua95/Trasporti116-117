package view.dialog;

import domain.ambulanza.AmbulanceDigitalTwin;
import domain.operatore.OperatorDigitalTwin;
import domain.paziente.PatientDigitalTwin;
import domain.trasporto.TransportDigitalTwin;
import javafx.scene.control.*;
import model.*;

import java.time.LocalDateTime;

public class TransportDialog extends DtDialog{

    @Override
    public void createEntity(){
        initialize("Aggiungi Trasporto");
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
        PatientDigitalTwin.getAllPatientId().forEach(p -> paziente.getItems().add(p.getFiscalCode()));
        gridPane.add(new Label("Paziente"), 0, 15);
        gridPane.add(paziente, 1, 15);

        ComboBox<String> operatore = new ComboBox<>();
        OperatorDigitalTwin.getAllOperatoreId().forEach(o -> operatore.getItems().add(o.getOperatorId()));
        gridPane.add(new Label("Operatore"), 0, 16);
        gridPane.add(operatore, 1, 16);

        ComboBox<String> ambulanza = new ComboBox<>();
        AmbulanceDigitalTwin.getAllAmbulanzaIdTwins().forEach(a -> ambulanza.getItems().add(a.getAmbulanceId()));
        gridPane.add(new Label("Ambulanza"), 0, 17);
        gridPane.add(ambulanza, 1, 17);

        dialog.getDialogPane().setContent(gridPane);

        dialog.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> TransportDigitalTwin.createTrasporto(
                        LocalDateTime.of(dataTrasporto.getValue().getYear(),
                                dataTrasporto.getValue().getMonth(),
                                dataTrasporto.getValue().getDayOfMonth(),
                                Integer.parseInt(hourTrasporto.getText()),
                                Integer.parseInt(minTrasporto.getText())),
                        TransportState.NOT_STARTED,
                        new Route(
                                new Location(new Address(viaPartenza.getText()),
                                        new HouseNumber(numeroPartenza.getText()),
                                        new City(cittàPartenza.getText()),
                                        new District(provinciaPartenza.getText()),
                                        new PostalCode(capPartenza.getText())),
                                new Location(new Address(viaArrivo.getText()),
                                        new HouseNumber(numeroArrivo.getText()),
                                        new City(cittàArrivo.getText()),
                                        new District(provinciaArrivo.getText()),
                                        new PostalCode(capArrivo.getText()))),
                        new AmbulanceId(ambulanza.getValue()),
                        new FiscalCode(paziente.getValue()),
                        new OperatorId(operatore.getValue())));

    }

    @Override
    public void deleteEntity() {
        initialize("Elimina Trasporto");

        ComboBox<String> transport = new ComboBox<>();
        TransportDigitalTwin.getAllTransportId().forEach(t -> transport.getItems().add(t.getId()));
        gridPane.add(new Label("Trasporto"), 0, 17);
        gridPane.add(transport, 1, 17);

        dialog.getDialogPane().setContent(gridPane);

        dialog.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> AmbulanceDigitalTwin.deleteAmbulanza(transport.getValue()));
    }
}
