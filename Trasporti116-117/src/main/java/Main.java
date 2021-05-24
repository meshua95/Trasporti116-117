/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

import com.azure.digitaltwins.core.BasicRelationship;
import com.azure.digitaltwins.core.implementation.models.ErrorResponseException;
import digitalTwins.Client;
import digitalTwins.DigitalTwinEraser;
import digitalTwins.DigitalTwinsBuilder;
import domain.ambulanza.StatoAmbulanza;
import domain.paziente.Autonomia;
import domain.paziente.DatiAnagraficiPaziente;
import domain.paziente.StatoDiSalute;
import domain.trasporto.DatiAnagraficiOperatore;
import domain.trasporto.Itinerario;
import domain.trasporto.StatoTrasporto;

import junit.extensions.*;
import org.junit.Test;


public class Main {
    public static void main(String... arg) {
        Client.createClient();
        //DigitalTwinEraser.deleteTwins(Arrays.asList("ambulanza2","GPS2"));
        DigitalTwinsBuilder.createAmbulanzaDigitalTwin(StatoAmbulanza.PRONTA, 2);

     /*   DigitalTwinsBuilder.createPazienteDigitalTwin(
                "paziente1",
                new DatiAnagraficiPaziente(
                        "Mario",
                        "Rossi",
                        LocalDate.of(1988, 1,8),
                        new DatiAnagraficiPaziente.Residenza("settembre","13B","Cesena", "FC", 47521)),
                new StatoDiSalute("Niente da riferire"),
                Autonomia.PARZIALMENTE_AUTONOMO
                );

        DigitalTwinsBuilder.createOperatoreAmbulanzaDigitalTwin(
                "operatore1",
                new DatiAnagraficiOperatore(
                        "Francesco",
                        "Bianchi",
                        LocalDate.of(1981, 4, 3),
                        new DatiAnagraficiOperatore.Residenza("ferrari", "111A", "Forlì", "FC", 47122)
                )
        );

        DigitalTwinsBuilder.createTrasportoDigitalTwin("Trasporto1",
                LocalDateTime.of(2021,05,05,18,00),
                StatoTrasporto.CONCLUSO,
                new Itinerario(
                        new Itinerario.Luogo("settembre","13B","Cesena", "FC", 47521),
                        new Itinerario.Luogo("corso cavour","189C","Cesena", "FC", 47521)),
                "ambulanza1",
                "paziente1",
                "operatore1");*/
    }

}