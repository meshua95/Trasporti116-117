/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicRelationship;
import com.azure.digitaltwins.core.implementation.models.ErrorResponseException;
import digitalTwins.Client;
import digitalTwins.ambulance.AmbulanceDigitalTwin;
import digitalTwins.booking.BookingDigitalTwin;
import digitalTwins.operator.OperatorDigitalTwin;
import digitalTwins.patient.PatientDigitalTwin;
import digitalTwins.request.ServiceRequestDigitalTwin;
import digitalTwins.transport.TransportDigitalTwin;
import domain.ambulanceBoundedContext.AmbulanceId;
import domain.patientBoundedContext.*;
import domain.requestBoundedContext.serviceRequest.BookingTransportId;
import domain.requestBoundedContext.serviceRequest.ServiceRequestId;
import domain.transportBoundedContext.*;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class DtTransport {
    private final PatientFiscalCode patientId = new PatientFiscalCode(TestDataValue.PATIENT_FISCAL_CODE);
    private final TransportId transportId = TransportDigitalTwin.generateTransportId(patientId, TestDataValue.TRANSPORT_DATE);
    private final OperatorId operatorId = new OperatorId(TestDataValue.OPERATOR_ID);
    private final ServiceRequestId serviceRequestId = ServiceRequestDigitalTwin.generateServiceRequestId(TestDataValue.SERVICE_REQUEST_DATE);
    private final BookingTransportId bookingId = BookingDigitalTwin.generateBookingTransportId(patientId, TestDataValue.BOOKING_DATE);

    @BeforeClass
    public static void createConnection(){
        Client.getClient();
    }

    private void createPatient() {
        PatientPersonalData personalData =
                new PatientPersonalData(
                        TestDataValue.PATIENT_NAME,
                        TestDataValue.PATIENT_SURNAME,
                        TestDataValue.PATIENT_BIRTHDAY,
                        TestDataValue.PATIENT_RESIDENCE);

        PatientDigitalTwin.createPatient(patientId, personalData, TestDataValue.HEALTH_STATE, Autonomy.NOT_AUTONOMOUS);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void createServiceRequest(){
        ServiceRequestDigitalTwin.createServiceRequest(TestDataValue.SERVICE_REQUEST_DATE);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void createBooking(){
        createServiceRequest();
        createPatient();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        BookingDigitalTwin.createBookingTransport(TestDataValue.BOOKING_DATE, TestDataValue.BOOKING_ROUTE, patientId, serviceRequestId);
    }

    private void createAmbulance(){
        AmbulanceDigitalTwin.createAmbulance(TestDataValue.AMBULANCE_NUMBER);
    }

    private void createOperator(){
        OperatorPersonalData personalData =
                new OperatorPersonalData(TestDataValue.OPERATOR_NAME,
                        TestDataValue.OPERATOR_SURNAME,
                        TestDataValue.OPERATOR_BIRTHDAY,
                        TestDataValue.OPERATOR_RESIDENCE);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        OperatorDigitalTwin.createOperator(operatorId, personalData);
    }

    private void createTransport(){
        createBooking();
        createAmbulance();
        createOperator();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TransportDigitalTwin.startTransport(
                bookingId,
                new AmbulanceId(TestDataValue.AMBULANCE_NUMBER),
                operatorId);
    }

    private void deleteAllTestDigitalTwin(){
        AmbulanceDigitalTwin.deleteAmbulance(new AmbulanceId(TestDataValue.AMBULANCE_NUMBER));
        OperatorDigitalTwin.deleteOperatore(operatorId);
        BookingDigitalTwin.deleteBookingTransport(bookingId);
        ServiceRequestDigitalTwin.deleteServiceRequest(serviceRequestId);
        PatientDigitalTwin.deletePatient(patientId);
    }

    @Test
    public void checkTransportCreation(){
        createTransport();

        TransportDigitalTwin.setTransportEnded(transportId);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(Client.getClient().getDigitalTwin(transportId.getId(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);

        TransportDigitalTwin.deleteTransport(transportId);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        deleteAllTestDigitalTwin();
    }

    @Test
    public void checkTransportAmbulanceRelationship(){
        createTransport();
        assertEquals(Client.getClient().getRelationship(transportId.getId(), transportId.getId() + "to" + new AmbulanceId(TestDataValue.AMBULANCE_NUMBER).getAmbulanceId(), BasicRelationship.class).getClass(), BasicRelationship.class);

        TransportDigitalTwin.deleteTransport(transportId);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

        TransportDigitalTwin.deleteTransport(transportId);
        try{
            Client.getClient().getDigitalTwin(transportId.getId(), BasicDigitalTwin.class);
        } catch (Exception ex){
            assertEquals(ex.getClass(), ErrorResponseException.class);
        }

        deleteAllTestDigitalTwin();
    }

}
