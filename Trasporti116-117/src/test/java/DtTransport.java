/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicRelationship;
import com.azure.digitaltwins.core.implementation.models.ErrorResponseException;
import digitalTwins.Client;
import digitalTwins.ambulance.AmbulanceDigitalTwin;
import digitalTwins.operator.OperatorDigitalTwin;
import digitalTwins.patient.PatientDigitalTwin;
import digitalTwins.transport.TransportDigitalTwin;
import domain.*;
import domain.ambulanceBoundedContext.AmbulanceId;
import domain.patientBoundedContext.*;
import domain.transportBoundedContext.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class DtTransport {
    private final PatientFiscalCode patientId = new PatientFiscalCode("paziente0");
    private final LocalDateTime dateTime = LocalDateTime.of(2021,05,05,18,00);
    private final TransportId transportId = TransportDigitalTwin.generateTransportId(patientId, dateTime);
    private final int ambulanceNumber = 0;
    private final OperatorId operatorId = new OperatorId("OP00");

    @BeforeClass
    public static void createConnection(){
        Client.getClient();
    }

    private void createAmbulance(){
        AmbulanceDigitalTwin.createAmbulance(ambulanceNumber);
    }

    private void createPatient(){
        PatientPersonalData personalData =
                new PatientPersonalData(
                        "Francesco",
                        "Bianchi",
                        LocalDate.of(1981, 4, 3),
                        new PatientResidence(new Address("Ferrari"), new HouseNumber("111A"), new City("Forlì"), new District("FC"), new PostalCode(47122))
                );
        HealthState healthState = new HealthState("Niente da riferire");

        PatientDigitalTwin.createPatient(patientId, personalData, healthState, Autonomy.NOT_AUTONOMOUS);
    }

    private void createOperatore(){
        OperatorPersonalData personalData =
                new OperatorPersonalData("Mario",
                        "Rossi",
                        LocalDate.of(1988, 1,8),
                        new OperatorResidence(new Address("IV Settembre"),new HouseNumber("13B"),new City("Cesena"), new District("FC"), new PostalCode(47521)));

        OperatorDigitalTwin.createOperator(operatorId, personalData);
    }

    private void createTrasporto(){
        createAmbulance();
        createPatient();
        createOperatore();

        TransportDigitalTwin.createTransport(
                dateTime,
                new TransportRoute(
                        new TransportLocation(new Address("IV Settembre"),new HouseNumber("13B"),new City("Cesena"), new District("FC"), new PostalCode(47521)),
                        new TransportLocation(new Address("corso cavour"),new HouseNumber("189C"),new City("Cesena"), new District("FC"), new PostalCode(47521))),
                new AmbulanceId(ambulanceNumber),
                patientId,
                operatorId);
    }

    private void deleteAllTestDigitalTwin(){
        AmbulanceDigitalTwin.deleteAmbulance(new AmbulanceId(ambulanceNumber));
        PatientDigitalTwin.deletePatient(patientId);
        OperatorDigitalTwin.deleteOperatore(operatorId);
    }

    @Test
    public void checkTrasporto(){
        createTrasporto();

        assertEquals(Client.getClient().getDigitalTwin(transportId.getId(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);

        TransportDigitalTwin.deleteTransport(transportId);
        deleteAllTestDigitalTwin();
    }

    @Test
    public void checkTrasportoAmbulanzaRelationship(){
        createTrasporto();
        assertEquals(Client.getClient().getRelationship(transportId.getId(), transportId.getId() + "to" + new AmbulanceId(ambulanceNumber).getAmbulanceId(), BasicRelationship.class).getClass(), BasicRelationship.class);

        TransportDigitalTwin.deleteTransport(transportId);
        deleteAllTestDigitalTwin();
    }

    @Test
    public void checkTrasportoPazienteRelationship(){
        createTrasporto();
        assertEquals(Client.getClient().getRelationship(transportId.getId(), transportId.getId() + "to" + patientId.getFiscalCode(), BasicRelationship.class).getClass(), BasicRelationship.class);

        TransportDigitalTwin.deleteTransport(transportId);
        deleteAllTestDigitalTwin();
    }

    @Test
    public void checkTransportOperatorRelationship(){
        createTrasporto();
        assertEquals(Client.getClient().getRelationship(transportId.getId(), transportId.getId() + "to" + operatorId.getOperatorId(), BasicRelationship.class).getClass(), BasicRelationship.class);

        TransportDigitalTwin.deleteTransport(transportId);
        deleteAllTestDigitalTwin();
    }

    @Test
    public void deleteTransport(){
        createTrasporto();
        System.out.println("delete trasporto " + transportId.getId());
        TransportDigitalTwin.deleteTransport(transportId);
        try{
            Client.getClient().getDigitalTwin(transportId.getId(), BasicDigitalTwin.class);
        } catch (Exception ex){
            assertEquals(ex.getClass(), ErrorResponseException.class);
        }

        deleteAllTestDigitalTwin();
    }
}
