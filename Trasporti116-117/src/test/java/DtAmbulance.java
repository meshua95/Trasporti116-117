/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */


import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicRelationship;
import com.azure.digitaltwins.core.implementation.models.ErrorResponseException;
import digitalTwins.Client;
import digitalTwins.ambulance.AmbulanceDigitalTwin;
import domain.ambulanceBoundedContext.AmbulanceId;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.errorCode.DeleteAmbulanceStatusCode;

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
        AmbulanceDigitalTwin.createAmbulance(ambulanceId);

        assertEquals(Client.getClient().getDigitalTwin(this.ambulanceId.getAmbulanceId(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);
        assertEquals(Client.getClient().getDigitalTwin(this.ambulanceId.getGpsId(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);
        assertEquals(Client.getClient().getRelationship(ambulanceId.getAmbulanceId(), ambulanceId.getAmbulanceId() + "to" + ambulanceId.getGpsId(), BasicRelationship.class).getClass(), BasicRelationship.class);

        AmbulanceDigitalTwin.deleteAmbulance(ambulanceId);
    }

    @Test
    public void createAmbulanceWithAmbulanceNumber(){
        AmbulanceDigitalTwin.createAmbulance(ambulanceNumber);

        assertEquals(Client.getClient().getDigitalTwin(new AmbulanceId(ambulanceNumber).getAmbulanceId(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);
        assertEquals(Client.getClient().getDigitalTwin(new AmbulanceId(ambulanceNumber).getGpsId(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);
        assertEquals(Client.getClient().getRelationship(new AmbulanceId(ambulanceNumber).getAmbulanceId(), new AmbulanceId(ambulanceNumber).getAmbulanceId() + "to" + new AmbulanceId(ambulanceNumber).getGpsId(), BasicRelationship.class).getClass(), BasicRelationship.class);

        AmbulanceDigitalTwin.deleteAmbulance(ambulanceId);
    }

    @Test
    public void deleteAmbulanceWithNoRelationship(){
        AmbulanceId ambulanceId = new AmbulanceId(ambulanceNumber);
        AmbulanceDigitalTwin.createAmbulance(ambulanceNumber);

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
/*
    @Test
    public void deleteAmbulanceWithRelationship(){
        Route route = new Route(new PatientResidence(new PatientAddress("corso cavour"), new PatientHouseNumber("3A"), new PatientCity("Cesena"), new PatientDistrict("FC"), new PatientPostalCode(47521)),
                                new PatientResidence(new PatientAddress ("Ferrari"), new PatientHouseNumber("111A"), new PatientCity("Forlì"), new PatientDistrict("FC"), new PatientPostalCode(47122)));

        PatientPersonalData personalData =
                new PatientPersonalData(
                        "Francesco",
                        "Bianchi",
                        LocalDate.of(1981, 4, 3),
                        new PatientResidence(new Address ("Ferrari"), new PatientHouseNumber("111A"), new PatientCity("Forlì"), new PatientDistrict("FC"), new PatientPostalCode(47122))
                );
        HealthState healthState = new HealthState("Niente da riferire");
        PatientFiscalCode patientId = new PatientFiscalCode("paziente0");
        PatientDigitalTwin.createPatient(patientId, personalData, healthState, Autonomy.NOT_AUTONOMOUS);

        OperatorId operatorId = new OperatorId("OP00");
        PatientPersonalData personalOperatorData =
                new PatientPersonalData("Mario",
                        "Rossi",
                        LocalDate.of(1988, 1,8),
                        new PatientResidence(new Address("IV Settembre"),new PatientHouseNumber("13B"),new PatientCity("Cesena"), new PatientDistrict("FC"), new PatientPostalCode(47521)));
        OperatorDigitalTwin.createOperator(operatorId, personalOperatorData);

        AmbulanceDigitalTwin.createAmbulance(AmbulanceState.MAINTENACE, ambulanceId);

        LocalDateTime dateTime = LocalDateTime.of(2021,05,05,18,00);
        BookingTransportId serviceRequestId = BookingDigitalTwin.createBookingTransport(dateTime, TransportState.NOT_STARTED, route, ambulanceId, patientId, operatorId);

        int deleteAmbulanceResult = AmbulanceDigitalTwin.deleteAmbulance(ambulanceId).getValue();
        assertEquals(deleteAmbulanceResult, DeleteAmbulanceStatusCode.TRANSPORT_RELATION_EXISTING.getValue());
        assertEquals(Client.getClient().getDigitalTwin(this.ambulanceId.getGpsId(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);
        assertEquals(Client.getClient().getDigitalTwin(this.ambulanceId.getAmbulanceId(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);


        BookingDigitalTwin.deleteBookingTransport(serviceRequestId);
        OperatorDigitalTwin.deleteOperatore(operatorId);
        PatientDigitalTwin.deletePatient(patientId);
        AmbulanceDigitalTwin.deleteAmbulance(ambulanceId);
    }*/
}
