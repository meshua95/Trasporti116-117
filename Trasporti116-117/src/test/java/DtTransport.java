/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicRelationship;
import com.azure.digitaltwins.core.implementation.models.ErrorResponseException;
import digitalTwins.Client;
import domain.ambulanza.AmbulanceDigitalTwin;
import model.*;
import domain.operatore.OperatorDigitalTwin;
import domain.paziente.PatientDigitalTwin;
import domain.trasporto.TransportDigitalTwin;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class DtTransport {
    private final FiscalCode patientId = new FiscalCode("paziente0");
    private final LocalDateTime dateTime = LocalDateTime.of(2021,05,05,18,00);
    private final TransportId transportId = TransportDigitalTwin.generateTransportId(patientId, dateTime);
    private final int ambulanceNumber = 0;
    private final OperatorId operatorId = new OperatorId("OP00");

    @BeforeClass
    public static void createConnection(){
        Client.getClient();
    }

    private void createAmbulance(){
        AmbulanceDigitalTwin.createAmbulance(AmbulanceState.READY, ambulanceNumber);
    }

    private void createPatient(){
        PersonalData personalData =
                new PersonalData(
                        "Francesco",
                        "Bianchi",
                        LocalDate.of(1981, 4, 3),
                        new Location(new Address ("Ferrari"), new HouseNumber("111A"), new City("Forlì"), new District("FC"), new PostalCode(47122))
                );
        HealthState healthState = new HealthState("Niente da riferire");

        PatientDigitalTwin.createPatient(patientId, personalData, healthState, Autonomy.NOT_AUTONOMOUS);
    }

    private void createOperatore(){
        PersonalData personalData =
                new PersonalData("Mario",
                        "Rossi",
                        LocalDate.of(1988, 1,8),
                        new Location(new Address("IV Settembre"),new HouseNumber("13B"),new City("Cesena"), new District("FC"), new PostalCode(47521)));

        OperatorDigitalTwin.createOperator(operatorId, personalData);
    }

    private void createTrasporto(){
        TransportDigitalTwin.createTransport(
                dateTime,
                TransportState.CANCELLED,
                new Route(
                        new Location(new Address("IV Settembre"),new HouseNumber("13B"),new City("Cesena"), new District("FC"), new PostalCode(47521)),
                        new Location(new Address("corso cavour"),new HouseNumber("189C"),new City("Cesena"), new District("FC"), new PostalCode(47521))),
                new AmbulanceId(ambulanceNumber),
                patientId,
                operatorId);
    }

    @Test
    public void checkTrasporto(){
        createAmbulance();
        createPatient();
        createOperatore();

        createTrasporto();

        assertEquals(Client.getClient().getDigitalTwin(transportId.getId(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);
    }

    @Test
    public void checkTrasportoAmbulanzaRelationship(){
        createTrasporto();
        assertEquals(Client.getClient().getRelationship(transportId.getId(), transportId.getId() + "to" + new AmbulanceId(ambulanceNumber).getAmbulanceId(), BasicRelationship.class).getClass(), BasicRelationship.class);
    }

    @Test
    public void checkTrasportoPazienteRelationship(){
        createTrasporto();
        assertEquals(Client.getClient().getRelationship(transportId.getId(), transportId.getId() + "to" + patientId.getFiscalCode(), BasicRelationship.class).getClass(), BasicRelationship.class);
    }

    @Test
    public void checkTransportOperatorRelationship(){
        createTrasporto();
        assertEquals(Client.getClient().getRelationship(transportId.getId(), transportId.getId() + "to" + operatorId.getOperatorId(), BasicRelationship.class).getClass(), BasicRelationship.class);
    }

    @Test
    public void deleteTransport(){
        try{
            TransportDigitalTwin.deleteTransport(transportId);
        } catch (Exception ex){
            assertEquals(ex.getClass(), ErrorResponseException.class);
        }
    }
}
