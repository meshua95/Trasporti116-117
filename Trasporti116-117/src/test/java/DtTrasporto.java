/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicRelationship;
import com.azure.digitaltwins.core.implementation.models.ErrorResponseException;
import digitalTwins.Client;
import domain.ambulanza.AmbulanzaDigitalTwin;
import model.*;
import domain.operatore.OperatoreDigitalTwin;
import domain.paziente.PazienteDigitalTwin;
import domain.trasporto.TrasportoDigitalTwin;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class DtTrasporto {
    private final String idTrasporto = "Trasporto1";
    private final int idAmbulanza = 3;
    private final String idPaziente = "CRGMHI12M21E730X";
    private final String idOperatore = "RSSMRA88A08E730X";

    @BeforeClass
    public static void createConnection(){
        Client.getClient();
    }

    private void createAmbulanza(){
        AmbulanzaDigitalTwin.createAmbulanza(AmbulanceState.READY, idAmbulanza);
    }

    private void createPaziente(){
        PersonalData datiAnagrafici =
                new PersonalData(
                        "Francesco",
                        "Bianchi",
                        LocalDate.of(1981, 4, 3),
                        new Location(new Address ("Ferrari"), new HouseNumber("111A"), new City("Forlì"), new District("FC"), new PostalCode("47122"))
                );
        HealthState statoSalute = new HealthState("Niente da riferire");

        PazienteDigitalTwin.createPaziente(idPaziente, datiAnagrafici, statoSalute, Autonomy.NOT_AUTONOMOUS);
    }

    private void createOperatore(){
        PersonalData personalData =
                new PersonalData("Mario",
                        "Rossi",
                        LocalDate.of(1988, 1,8),
                        new Location(new Address("IV Settembre"),new HouseNumber("13B"),new City("Cesena"), new District("FC"), new PostalCode("47521")));

        OperatoreDigitalTwin.createOperatore(idOperatore, personalData);
    }

    private void createTrasporto(){
        TrasportoDigitalTwin.createTrasporto(idTrasporto,
                LocalDateTime.of(2021,05,05,18,00),
                TransportState.ENDED,
                new Itinerario(
                        new Location(new Address("IV Settembre"),new HouseNumber("13B"),new City("Cesena"), new District("FC"), new PostalCode("47521")),
                        new Location(new Address("corso cavour"),new HouseNumber("189C"),new City("Cesena"), new District("FC"), new PostalCode("47521"))),
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
            TrasportoDigitalTwin.deleteTrasporto(idTrasporto);
        } catch (Exception ex){
            assertEquals(ex.getClass(), ErrorResponseException.class);
        }
    }
}
