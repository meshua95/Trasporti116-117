package view.dialog;

import digitalTwins.DigitalTwinQuery;
import digitalTwins.DigitalTwinsBuilder;
import domain.trasporto.Itinerario;
import domain.trasporto.StatoTrasporto;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.time.LocalDateTime;

public class TransportDialog {
    public static void createTrasportoDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Aggiungi Trasporto");

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

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
        DigitalTwinQuery.getAllPazienteIdTwins().forEach(p -> paziente.getItems().add(p.getCodiceFiscale()));
        gridPane.add(new Label("Paziente"), 0, 15);
        gridPane.add(paziente, 1, 15);

        ComboBox<String> operatore = new ComboBox<>();
        DigitalTwinQuery.getAllOperatoreIdTwins().forEach(o -> operatore.getItems().add(o.getCodiceFiscale()));
        gridPane.add(new Label("Operatore"), 0, 16);
        gridPane.add(operatore, 1, 16);

        ComboBox<String> ambulanza = new ComboBox<>();
        DigitalTwinQuery.getAllAmbulanzaIdTwins().forEach(a -> ambulanza.getItems().add(a.getIdAmbulanza()));
        gridPane.add(new Label("Ambulanza"), 0, 17);
        gridPane.add(ambulanza, 1, 17);

        dialog.getDialogPane().setContent(gridPane);

        dialog.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> DigitalTwinsBuilder.createTrasportoDigitalTwin("Trasporto2",
                        LocalDateTime.of(dataTrasporto.getValue().getYear(),
                                dataTrasporto.getValue().getMonth(),
                                dataTrasporto.getValue().getDayOfMonth(),
                                Integer.parseInt(hourTrasporto.getText()),
                                Integer.parseInt(minTrasporto.getText())),
                        StatoTrasporto.NON_INIZIATO,
                        new Itinerario(
                                new Itinerario.Luogo(viaPartenza.getText(),
                                        numeroPartenza.getText(),
                                        cittàPartenza.getText(),
                                        provinciaPartenza.getText(),
                                        Integer.parseInt(capPartenza.getText())),
                                new Itinerario.Luogo(viaArrivo.getText(),
                                        numeroArrivo.getText(),
                                        cittàArrivo.getText(),
                                        provinciaArrivo.getText(),
                                        Integer.parseInt(capArrivo.getText()))),
                        ambulanza.getValue(),
                        paziente.getValue(),
                        operatore.getValue()));

    }
}
