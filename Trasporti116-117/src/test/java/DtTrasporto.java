/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicRelationship;
import com.azure.digitaltwins.core.implementation.models.ErrorResponseException;
import digitalTwins.Client;
import digitalTwins.DigitalTwinEraser;
import digitalTwins.DigitalTwinsBuilder;
import domain.ambulanza.StatoAmbulanza;
import domain.paziente.Autonomia;
import domain.paziente.DatiAnagraficiPaziente;
import domain.paziente.StatoDiSalute;
import domain.operatore.DatiAnagraficiOperatore;
import domain.trasporto.Itinerario;
import domain.trasporto.StatoTrasporto;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class DtTrasporto {
    private final String idTrasporto = "Trasporto1";
    private final int idAmbulanza = 3;
    private final String idPaziente = "CRGMHI12M21E730X";
    private final String idOperatore = "RSSMRA88A08E730X";

    @BeforeClass
    public static void createConnection(){
        Client.createClient();
    }

    private void createAmbulanza(){
        DigitalTwinsBuilder.createAmbulanzaDigitalTwin(StatoAmbulanza.PRONTA, idAmbulanza);
    }

    private void createPaziente(){
        DatiAnagraficiPaziente datiAnagrafici =
                new DatiAnagraficiPaziente(
                        "Francesco",
                        "Bianchi",
                        LocalDate.of(1981, 4, 3),
                        new DatiAnagraficiPaziente.Residenza("ferrari", "111A", "Forlì", "FC", 47122)
                );
        StatoDiSalute statoSalute = new StatoDiSalute("Niente da riferire");

        DigitalTwinsBuilder.createPazienteDigitalTwin(idPaziente, datiAnagrafici, statoSalute, Autonomia.NON_AUTONOMO);
    }

    private void createOperatore(){
        DatiAnagraficiOperatore datiAnagrafici =
                new DatiAnagraficiOperatore("Mario",
                        "Rossi",
                        LocalDate.of(1988, 1,8),
                        new DatiAnagraficiOperatore.Residenza("settembre","13B","Cesena", "FC", 47521));

        DigitalTwinsBuilder.createOperatoreAmbulanzaDigitalTwin(idOperatore, datiAnagrafici);
    }

    private void createTrasporto(){
        DigitalTwinsBuilder.createTrasportoDigitalTwin(idTrasporto,
                LocalDateTime.of(2021,05,05,18,00),
                StatoTrasporto.CONCLUSO,
                new Itinerario(
                        new Itinerario.Luogo("settembre","13B","Cesena", "FC", 47521),
                        new Itinerario.Luogo("corso cavour","189C","Cesena", "FC", 47521)),
                "ambulanza" + idAmbulanza,
                idPaziente,
                idOperatore);
    }

    @Test
    public void checkTrasporto(){
        createAmbulanza();
        createPaziente();
        createOperatore();

        createTrasporto();

        assertEquals(Client.getClient().getDigitalTwin(idTrasporto, BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);
    }

    @Test
    public void checkTrasportoAmbulanzaRelationship(){
        createTrasporto();
        assertEquals(Client.getClient().getRelationship(idTrasporto, idTrasporto + "toambulanza" + idAmbulanza, BasicRelationship.class).getClass(), BasicRelationship.class);
    }

    @Test
    public void checkTrasportoPazienteRelationship(){
        createTrasporto();
        assertEquals(Client.getClient().getRelationship(idTrasporto, idTrasporto + "to" + idPaziente, BasicRelationship.class).getClass(), BasicRelationship.class);
    }

    @Test
    public void checkTrasportoOperatoreRelationship(){
        createTrasporto();
        assertEquals(Client.getClient().getRelationship(idTrasporto, idTrasporto + "to" + idOperatore, BasicRelationship.class).getClass(), BasicRelationship.class);
    }

    @Test
    public void deleteTrasporto(){
        try{
            DigitalTwinEraser.deleteTwins(Arrays.asList(idTrasporto));
        } catch (Exception ex){
            assertEquals(ex.getClass(), ErrorResponseException.class);
        }
    }
}
