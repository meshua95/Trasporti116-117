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
import domain.ambulanceBoundedContext.AmbulanceId;
import domain.patientBoundedContext.*;
import domain.transportBoundedContext.*;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DtTransport {
    private final PatientFiscalCode patientId = new PatientFiscalCode(TestDataValue.PATIENT_FISCAL_CODE);
    private final TransportId transportId = TransportDigitalTwin.generateTransportId(patientId, TestDataValue.TRANSPORT_DATE);
    private final OperatorId operatorId = new OperatorId(TestDataValue.OPERATOR_ID);

    @BeforeClass
    public static void createConnection(){
        Client.getClient();
    }

    private void createAmbulance(){
        AmbulanceDigitalTwin.createAmbulance(TestDataValue.AMBULANCE_NUMBER);
    }

    private void createPatient(){
        PatientPersonalData personalData =
                new PatientPersonalData(
                        TestDataValue.PATIENT_NAME,
                        TestDataValue.PATIENT_SURNAME,
                        TestDataValue.PATIENT_BIRTHDAY,
                        TestDataValue.PATIENT_RESIDENCE);

        PatientDigitalTwin.createPatient(patientId, personalData, TestDataValue.HEALTH_STATE, Autonomy.NOT_AUTONOMOUS);
    }

    private void createOperator(){
        OperatorPersonalData personalData =
                new OperatorPersonalData(TestDataValue.OPERATOR_NAME,
                        TestDataValue.OPERATOR_SURNAME,
                        TestDataValue.OPERATOR_BIRTHDAY,
                        TestDataValue.OPERATOR_RESIDENCE);

        OperatorDigitalTwin.createOperator(operatorId, personalData);
    }

    private void createTransport(){
        createAmbulance();
        createPatient();
        createOperator();

        TransportDigitalTwin.createTransport(
                TestDataValue.TRANSPORT_DATE,
                TestDataValue.TRANSPORT_ROUTE,
                new AmbulanceId(TestDataValue.AMBULANCE_NUMBER),
                patientId,
                operatorId);
    }

    private void deleteAllTestDigitalTwin(){
        AmbulanceDigitalTwin.deleteAmbulance(new AmbulanceId(TestDataValue.AMBULANCE_NUMBER));
        PatientDigitalTwin.deletePatient(patientId);
        OperatorDigitalTwin.deleteOperatore(operatorId);
    }

    @Test
    public void checkTransport(){
        createTransport();

        assertEquals(Client.getClient().getDigitalTwin(transportId.getId(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);

        TransportDigitalTwin.deleteTransport(transportId);
        deleteAllTestDigitalTwin();
    }

    @Test
    public void checkTransportAmbulanceRelationship(){
        createTransport();
        assertEquals(Client.getClient().getRelationship(transportId.getId(), transportId.getId() + "to" + new AmbulanceId(TestDataValue.AMBULANCE_NUMBER).getAmbulanceId(), BasicRelationship.class).getClass(), BasicRelationship.class);

        TransportDigitalTwin.deleteTransport(transportId);
        deleteAllTestDigitalTwin();
    }

    @Test
    public void checkTransportPatientRelationship(){
        createTransport();
        assertEquals(Client.getClient().getRelationship(transportId.getId(), transportId.getId() + "to" + patientId.getFiscalCode(), BasicRelationship.class).getClass(), BasicRelationship.class);

        TransportDigitalTwin.deleteTransport(transportId);
        deleteAllTestDigitalTwin();
    }

    @Test
    public void checkTransportOperatorRelationship(){
        createTransport();
        assertEquals(Client.getClient().getRelationship(transportId.getId(), transportId.getId() + "to" + operatorId.getOperatorId(), BasicRelationship.class).getClass(), BasicRelationship.class);

        TransportDigitalTwin.deleteTransport(transportId);
        deleteAllTestDigitalTwin();
    }

    @Test
    public void deleteTransport(){
        createTransport();
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
