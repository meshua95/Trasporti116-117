import digitalTwins.Client;
import digitalTwins.DigitalTwinsBuilder;
import domain.trasporto.Itinerario;
import domain.trasporto.StatoTrasporto;

import java.time.LocalDateTime;

public class Main {

    public static void main(String... arg) {
        Client.createClient();

        /*DigitalTwinsBuilder.createTrasportoDigitalTwin("Trasporto1",
                LocalDateTime.of(2021,05,05,18,00),
                StatoTrasporto.NON_INIZIATO,
                new Itinerario(
                        new Itinerario.Luogo("settembre","13B","Cesena", "FC", 47521),
                        new Itinerario.Luogo("corso cavour","189C","Cesena", "FC", 47521)));*/
        DigitalTwinsBuilder.createTrasportoDigitalTwin("Trasporto1",
                LocalDateTime.of(2021,05,05,18,00),
                StatoTrasporto.NON_INIZIATO,
                new Itinerario(
                        new Itinerario.Luogo("settembre","13B","Cesena", "FC", 47521),
                        new Itinerario.Luogo("corso cavour","189C","Cesena", "FC", 47521)),
                "ambulanza1",
                "paziente1",
                "OperatoreAmbulanza1");
    }
}