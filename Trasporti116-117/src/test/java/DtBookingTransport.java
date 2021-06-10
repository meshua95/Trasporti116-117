/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicRelationship;
import com.azure.digitaltwins.core.implementation.models.ErrorResponseException;
import digitalTwins.Client;
import digitalTwins.booking.BookingDigitalTwin;
import digitalTwins.request.ServiceRequestDigitalTwin;
import domain.patientBoundedContext.*;
import domain.requestBoundedContext.serviceRequest.*;
import digitalTwins.patient.PatientDigitalTwin;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DtBookingTransport {
    private final PatientFiscalCode patientId = new PatientFiscalCode(TestDataValue.PATIENT_FISCAL_CODE);
     final BookingTransportId bookingTransportId = BookingDigitalTwin.generateBookingTransportId(patientId, TestDataValue.BOOKING_DATE);
    private final ServiceRequestId serviceReqId = ServiceRequestDigitalTwin.generateServiceRequestId(TestDataValue.SERVICE_REQUEST_DATE);

    @BeforeClass
    public static void createConnection(){
        Client.getClient();
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

    public void createServiceRequest(){
        ServiceRequestDigitalTwin.createServiceRequest(TestDataValue.SERVICE_REQUEST_DATE);
    }

    private void createBooking(){
        createPatient();
        createServiceRequest();

        BookingDigitalTwin.createBookingTransport(
                TestDataValue.BOOKING_DATE,
                TestDataValue.BOOKING_ROUTE,
                patientId,
                serviceReqId);
    }

    private void deleteAllTestDigitalTwin(){
        PatientDigitalTwin.deletePatient(patientId);
    }

    @Test
    public void checkTrasporto(){
        createBooking();

        assertEquals(Client.getClient().getDigitalTwin(bookingTransportId.getId(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);

        BookingDigitalTwin.deleteBookingTransport(bookingTransportId);
        deleteAllTestDigitalTwin();
    }

    @Test
    public void checkBookingPatientRelationship(){
        createBooking();
        assertEquals(Client.getClient().getRelationship(bookingTransportId.getId(), bookingTransportId.getId() + "to" + patientId.getFiscalCode(), BasicRelationship.class).getClass(), BasicRelationship.class);

        BookingDigitalTwin.deleteBookingTransport(bookingTransportId);
        deleteAllTestDigitalTwin();
    }

    @Test
    public void checkBookingServiceRequestRelationship(){
        createBooking();
        assertEquals(Client.getClient().getRelationship(bookingTransportId.getId(), bookingTransportId.getId() + "to" + serviceReqId.getserviceRequestId(), BasicRelationship.class).getClass(), BasicRelationship.class);

        BookingDigitalTwin.deleteBookingTransport(bookingTransportId);
        deleteAllTestDigitalTwin();
    }

    @Test
    public void deleteTransport(){
        createBooking();
        BookingDigitalTwin.deleteBookingTransport(bookingTransportId);
        try{
            Client.getClient().getDigitalTwin(bookingTransportId.getId(), BasicDigitalTwin.class);
        } catch (Exception ex){
            assertEquals(ex.getClass(), ErrorResponseException.class);
        }

        deleteAllTestDigitalTwin();
    }
}
