/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */


import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicRelationship;
import com.azure.digitaltwins.core.implementation.models.ErrorResponseException;
import digitalTwins.Client;
import domain.ambulanza.AmbulanceDigitalTwin;
import domain.operatore.OperatorDigitalTwin;
import domain.paziente.PatientDigitalTwin;
import domain.trasporto.TransportDigitalTwin;
import model.*;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.errorCode.DeleteAmbulanceStatusCode;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class DtAmbulance {

    private final int ambulanceNumber = 0;
    private final AmbulanceId ambulanceId = new AmbulanceId(ambulanceNumber);

    @BeforeClass
    public static void createConnection(){
        Client.getClient();
    }

    //Quando si crea un'ambulanza, in automatico si deve creare anche il relativo GPS e la relazione che li collega
    @Test
    public void createAmbulanceWithAmbulanceId(){
        AmbulanceDigitalTwin.createAmbulance(AmbulanceState.READY, ambulanceId);

        assertEquals(Client.getClient().getDigitalTwin(this.ambulanceId.getAmbulanceId(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);
        assertEquals(Client.getClient().getDigitalTwin(this.ambulanceId.getGpsId(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);
        assertEquals(Client.getClient().getRelationship(ambulanceId.getAmbulanceId(), ambulanceId.getAmbulanceId() + "to" + ambulanceId.getGpsId(), BasicRelationship.class).getClass(), BasicRelationship.class);

        AmbulanceDigitalTwin.deleteAmbulance(ambulanceId);
    }

    @Test
    public void createAmbulanceWithAmbulanceNumber(){
        AmbulanceDigitalTwin.createAmbulance(AmbulanceState.READY, ambulanceNumber);

        assertEquals(Client.getClient().getDigitalTwin(new AmbulanceId(ambulanceNumber).getAmbulanceId(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);
        assertEquals(Client.getClient().getDigitalTwin(new AmbulanceId(ambulanceNumber).getGpsId(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);
        assertEquals(Client.getClient().getRelationship(new AmbulanceId(ambulanceNumber).getAmbulanceId(), new AmbulanceId(ambulanceNumber).getAmbulanceId() + "to" + new AmbulanceId(ambulanceNumber).getGpsId(), BasicRelationship.class).getClass(), BasicRelationship.class);

        AmbulanceDigitalTwin.deleteAmbulance(ambulanceId);
    }

    @Test
    public void deleteAmbulanceWithNoRelationship(){
        AmbulanceId ambulanceId = new AmbulanceId(ambulanceNumber);
        AmbulanceDigitalTwin.createAmbulance(AmbulanceState.MAINTENACE, ambulanceNumber);

        assertEquals(AmbulanceDigitalTwin.deleteAmbulance(ambulanceId), DeleteAmbulanceStatusCode.DELETED);
        try {
            Client.getClient().getDigitalTwin(ambulanceId.getAmbulanceId(), BasicDigitalTwin.class);
        } catch (Exception ex){
            assertEquals(ex.getClass(), ErrorResponseException.class);
        }
        try {
            Client.getClient().getDigitalTwin(ambulanceId.getGpsId(), BasicDigitalTwin.class);
        } catch (Exception ex){
            assertEquals(ex.getClass(), ErrorResponseException.class);
        }
    }

    @Test
    public void deleteAmbulanceWithRelationship(){
        Route route = new Route(new Location(new Address("corso cavour"), new HouseNumber("3A"), new City("Cesena"), new District("FC"), new PostalCode(47521)),
                                new Location(new Address ("Ferrari"), new HouseNumber("111A"), new City("Forlì"), new District("FC"), new PostalCode(47122)));

        PersonalData personalData =
                new PersonalData(
                        "Francesco",
                        "Bianchi",
                        LocalDate.of(1981, 4, 3),
                        new Location(new Address ("Ferrari"), new HouseNumber("111A"), new City("Forlì"), new District("FC"), new PostalCode(47122))
                );
        HealthState healthState = new HealthState("Niente da riferire");
        FiscalCode patientId = new FiscalCode("paziente0");
        PatientDigitalTwin.createPatient(patientId, personalData, healthState, Autonomy.NOT_AUTONOMOUS);

        OperatorId operatorId = new OperatorId("OP00");
        PersonalData personalOperatorData =
                new PersonalData("Mario",
                        "Rossi",
                        LocalDate.of(1988, 1,8),
                        new Location(new Address("IV Settembre"),new HouseNumber("13B"),new City("Cesena"), new District("FC"), new PostalCode(47521)));
        OperatorDigitalTwin.createOperator(operatorId, personalOperatorData);

        AmbulanceDigitalTwin.createAmbulance(AmbulanceState.MAINTENACE, ambulanceId);

        LocalDateTime dateTime = LocalDateTime.of(2021,05,05,18,00);
        TransportId transportId = TransportDigitalTwin.createTransport(dateTime, TransportState.NOT_STARTED, route, ambulanceId, patientId, operatorId);

        int deleteAmbulanceResult = AmbulanceDigitalTwin.deleteAmbulance(ambulanceId).getValue();
        assertEquals(deleteAmbulanceResult, DeleteAmbulanceStatusCode.TRANSPORT_RELATION_EXISTING.getValue());
        assertEquals(Client.getClient().getDigitalTwin(this.ambulanceId.getGpsId(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);
        assertEquals(Client.getClient().getDigitalTwin(this.ambulanceId.getAmbulanceId(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);


        TransportDigitalTwin.deleteTransport(transportId);
        OperatorDigitalTwin.deleteOperatore(operatorId);
        PatientDigitalTwin.deletePatient(patientId);
        AmbulanceDigitalTwin.deleteAmbulance(ambulanceId);
    }
}
