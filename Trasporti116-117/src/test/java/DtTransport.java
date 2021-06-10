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
    private final OperatorId operatorId = new OperatorId(TestDataValue.OPERATOR_ID);
    private final ServiceRequestId serviceRequestId = ServiceRequestDigitalTwin.generateServiceRequestId(TestDataValue.SERVICE_REQUEST_DATE);
    private BookingTransportId bookingId;
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
    }

    private void createServiceRequest(){
        ServiceRequestDigitalTwin.createServiceRequest(TestDataValue.SERVICE_REQUEST_DATE);
    }

    private BookingTransportId createBooking(){
        createServiceRequest();
        createPatient();
        return BookingDigitalTwin.createBookingTransport(TestDataValue.BOOKING_DATE, TestDataValue.BOOKING_ROUTE, patientId, serviceRequestId);
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
        OperatorDigitalTwin.createOperator(operatorId, personalData);
    }

    private TransportId createTransport(){
        bookingId = createBooking();
        createAmbulance();
        createOperator();

        return TransportDigitalTwin.startTransport(
                bookingId,
                new AmbulanceId(TestDataValue.AMBULANCE_NUMBER),
                operatorId);
    }

    private void deleteAllTestDigitalTwin(){
        BookingDigitalTwin.deleteBookingTransport(bookingId);
        AmbulanceDigitalTwin.deleteAmbulance(new AmbulanceId(TestDataValue.AMBULANCE_NUMBER));
        OperatorDigitalTwin.deleteOperatore(operatorId);
        ServiceRequestDigitalTwin.deleteServiceRequest(serviceRequestId);
        PatientDigitalTwin.deletePatient(patientId);
    }

    @Test
    public void checkTransportCreation(){
        TransportId transportId = createTransport();

        TransportDigitalTwin.setTransportEnded(transportId);
        assertEquals(Client.getClient().getDigitalTwin(transportId.getId(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);

        TransportDigitalTwin.deleteTransport(transportId);
        deleteAllTestDigitalTwin();
    }

    @Test
    public void checkTransportAmbulanceRelationship(){
        TransportId transportId = createTransport();
        assertEquals(Client.getClient().getRelationship(transportId.getId(), transportId.getId() + "to" + new AmbulanceId(TestDataValue.AMBULANCE_NUMBER).getAmbulanceId(), BasicRelationship.class).getClass(), BasicRelationship.class);

        TransportDigitalTwin.deleteTransport(transportId);
        deleteAllTestDigitalTwin();
    }

    @Test
    public void checkTransportPatientRelationship(){
        TransportId transportId = createTransport();
        assertEquals(Client.getClient().getRelationship(transportId.getId(), transportId.getId() + "to" + patientId.getFiscalCode(), BasicRelationship.class).getClass(), BasicRelationship.class);

        TransportDigitalTwin.deleteTransport(transportId);
        deleteAllTestDigitalTwin();
    }

    @Test
    public void checkTransportOperatorRelationship(){
        TransportId transportId = createTransport();
        assertEquals(Client.getClient().getRelationship(transportId.getId(), transportId.getId() + "to" + operatorId.getOperatorId(), BasicRelationship.class).getClass(), BasicRelationship.class);

        TransportDigitalTwin.deleteTransport(transportId);
        deleteAllTestDigitalTwin();
    }

    @Test
    public void deleteTransport(){
        createTransport();
        deleteAllTestDigitalTwin();
    }

}
