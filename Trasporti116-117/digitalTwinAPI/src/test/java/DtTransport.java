/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicRelationship;
import domain.transport.TransportId;
import domain.transport.ambulance.AmbulanceId;
import domain.request.serviceRequest.BookingTransportId;
import domain.request.serviceRequest.ServiceRequestId;
import digitaltwinsapi.Client;
import digitaltwinsapi.DeleteBookingTransport;
import digitaltwinsapi.DeletePatient;
import digitaltwinsapi.GenerateId;
import digitaltwinsapi.CreateBookingTransport;
import digitaltwinsapi.CreatePatient;
import digitaltwinsapi.CreateRequest;
import digitaltwinsapi.CreateAmbulance;
import digitaltwinsapi.CreateOperator;
import digitaltwinsapi.DeleteOperator;
import digitaltwinsapi.DeleteRequest;
import digitaltwinsapi.DeleteAmbulance;
import digitaltwinsapi.DeleteTransport;
import digitaltwinsapi.TransportEnded;
import digitaltwinsapi.StartTransport;
import domain.patient.Autonomy;
import domain.patient.PatientFiscalCode;
import domain.patient.PatientPersonalData;
import domain.transport.operator.OperatorId;
import domain.transport.operator.OperatorPersonalData;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.errorCode.QueryTimeOutException;

import static org.junit.Assert.assertEquals;


public class DtTransport {
    private final PatientFiscalCode patientId = new PatientFiscalCode(TestDataValue.PATIENT_FISCAL_CODE);
    private final OperatorId operatorId = new OperatorId(TestDataValue.OPERATOR_ID);
    private final ServiceRequestId serviceRequestId = GenerateId.generateServiceRequestId(TestDataValue.SERVICE_REQUEST_DATE);
    private BookingTransportId bookingId;

    @BeforeClass
    public static void createConnection() {
        Client.getClient();
    }

    private synchronized void createPatient() {
        PatientPersonalData personalData =
                new PatientPersonalData(
                        TestDataValue.PATIENT_NAME,
                        TestDataValue.PATIENT_SURNAME,
                        TestDataValue.PATIENT_BIRTHDAY,
                        TestDataValue.PATIENT_RESIDENCE);

        CreatePatient.createPatient(patientId, personalData, TestDataValue.HEALTH_STATE, Autonomy.NOT_AUTONOMOUS);
    }

    private synchronized void createServiceRequest() {
        CreateRequest.createServiceRequest(TestDataValue.SERVICE_REQUEST_DATE);
    }

    private synchronized BookingTransportId createBooking() {
        createServiceRequest();
        createPatient();
        return CreateBookingTransport.createBookingTransport(TestDataValue.BOOKING_DATE, TestDataValue.BOOKING_ROUTE, patientId, serviceRequestId);
    }

    private synchronized void createAmbulance() {
        CreateAmbulance.createAmbulance(TestDataValue.AMBULANCE_NUMBER);
    }

    private synchronized void createOperator() {
        OperatorPersonalData personalData =
                new OperatorPersonalData(TestDataValue.OPERATOR_NAME,
                        TestDataValue.OPERATOR_SURNAME,
                        TestDataValue.OPERATOR_BIRTHDAY,
                        TestDataValue.OPERATOR_RESIDENCE);
        CreateOperator.createOperator(operatorId, personalData);
    }

    private synchronized TransportId createTransport() throws QueryTimeOutException {
        bookingId = createBooking();
        createAmbulance();
        createOperator();

        return StartTransport.startTransport(
                bookingId,
                new AmbulanceId(TestDataValue.AMBULANCE_NUMBER),
                operatorId);
    }

    private void deleteAllTestDigitalTwin() {
        DeleteBookingTransport.deleteBookingTransport(bookingId);
        DeleteAmbulance.deleteAmbulance(new AmbulanceId(TestDataValue.AMBULANCE_NUMBER));
        DeleteOperator.deleteOperator(operatorId);
        DeleteRequest.deleteServiceRequest(serviceRequestId);
        DeletePatient.deletePatient(patientId);
    }

    @Test
    public void checkTransportCreation() throws QueryTimeOutException {
        TransportId transportId = createTransport();

        TransportEnded.setTransportEnded(transportId);
        assertEquals(TestDataValue.EQUALS_DT, Client.getClient().getDigitalTwin(transportId.getId(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);

        DeleteTransport.deleteTransport(transportId);
        deleteAllTestDigitalTwin();
    }

    @Test
    public void checkTransportAmbulanceRelationship() throws QueryTimeOutException {
        TransportId transportId = createTransport();
        assertEquals(TestDataValue.EQUALS_REL, Client.getClient().getRelationship(transportId.getId(), transportId.getId() + "to" + new AmbulanceId(TestDataValue.AMBULANCE_NUMBER).getId(), BasicRelationship.class).getClass(), BasicRelationship.class);

        DeleteTransport.deleteTransport(transportId);
        deleteAllTestDigitalTwin();
    }

    @Test
    public void checkTransportPatientRelationship() throws QueryTimeOutException {
        TransportId transportId = createTransport();
        assertEquals(TestDataValue.EQUALS_REL, Client.getClient().getRelationship(transportId.getId(), transportId.getId() + "to" + patientId.getFiscalCode(), BasicRelationship.class).getClass(), BasicRelationship.class);

        DeleteTransport.deleteTransport(transportId);
        deleteAllTestDigitalTwin();
    }

    @Test
    public void checkTransportOperatorRelationship() throws QueryTimeOutException {
        TransportId transportId = createTransport();
        assertEquals(TestDataValue.EQUALS_REL, Client.getClient().getRelationship(transportId.getId(), transportId.getId() + "to" + operatorId.getId(), BasicRelationship.class).getClass(), BasicRelationship.class);

        DeleteTransport.deleteTransport(transportId);
        deleteAllTestDigitalTwin();
    }

}
