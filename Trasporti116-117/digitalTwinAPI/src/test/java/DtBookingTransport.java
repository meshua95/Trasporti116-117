/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicRelationship;
import com.azure.digitaltwins.core.implementation.models.ErrorResponseException;

import digitaltwinsapi.Client;
import digitaltwinsapi.DeleteBookingTransport;
import digitaltwinsapi.DeletePatient;
import digitaltwinsapi.GenerateId;
import digitaltwinsapi.CreateBookingTransport;
import digitaltwinsapi.CreatePatient;
import digitaltwinsapi.CreateRequest;
import domain.patient.Autonomy;
import domain.patient.PatientFiscalCode;
import domain.patient.PatientPersonalData;
import domain.request.serviceRequest.BookingTransportId;
import domain.request.serviceRequest.ServiceRequestId;
import digitaltwinsapi.SetTakeOwnership;
import digitaltwinsapi.GetBooking;

import org.junit.BeforeClass;
import org.junit.Test;
import utils.errorCode.QueryTimeOutException;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DtBookingTransport {
    private final PatientFiscalCode patientId = new PatientFiscalCode(TestDataValue.PATIENT_FISCAL_CODE);
    private final BookingTransportId bookingTransportId = GenerateId.generateBookingTransportId(patientId, TestDataValue.BOOKING_DATE);
    private final ServiceRequestId serviceReqId = GenerateId.generateServiceRequestId(TestDataValue.SERVICE_REQUEST_DATE);

    @BeforeClass
    public static void createConnection() {
        Client.getClient();
    }

    private void createPatient() {
        PatientPersonalData personalData =
                new PatientPersonalData(
                        TestDataValue.PATIENT_NAME,
                        TestDataValue.PATIENT_SURNAME,
                        TestDataValue.PATIENT_BIRTHDAY,
                        TestDataValue.PATIENT_RESIDENCE);

        CreatePatient.createPatient(patientId, personalData, TestDataValue.HEALTH_STATE, Autonomy.NOT_AUTONOMOUS);
    }

    private void createServiceRequest() {
        CreateRequest.createServiceRequest(TestDataValue.SERVICE_REQUEST_DATE);
    }

    private void createBooking() {
        createPatient();
        createServiceRequest();

        CreateBookingTransport.createBookingTransport(
                TestDataValue.BOOKING_DATE,
                TestDataValue.BOOKING_ROUTE,
                patientId,
                serviceReqId);
    }

    private void deleteAllTestDigitalTwin() {
        DeletePatient.deletePatient(patientId);
    }

    @Test
    public void checkBookingCreation() {
        createBooking();

        assertEquals(TestDataValue.EQUALS_DT, Client.getClient().getDigitalTwin(bookingTransportId.getId(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);

        DeleteBookingTransport.deleteBookingTransport(bookingTransportId);
        deleteAllTestDigitalTwin();
    }

    @Test
    public void checkBookingPatientRelationship() {
        createBooking();
        assertEquals(TestDataValue.EQUALS_REL, Client.getClient().getRelationship(bookingTransportId.getId(), bookingTransportId.getId() + "to" + patientId.getFiscalCode(), BasicRelationship.class).getClass(), BasicRelationship.class);

        DeleteBookingTransport.deleteBookingTransport(bookingTransportId);
        deleteAllTestDigitalTwin();
    }

    @Test
    public void checkBookingServiceRequestRelationship() {
        createBooking();
        assertEquals(TestDataValue.EQUALS_REL, Client.getClient().getRelationship(bookingTransportId.getId(), bookingTransportId.getId() + "to" + serviceReqId.getId(), BasicRelationship.class).getClass(), BasicRelationship.class);

        DeleteBookingTransport.deleteBookingTransport(bookingTransportId);
        deleteAllTestDigitalTwin();
    }

    @Test
    public void checkBookingTakeOwnership() throws QueryTimeOutException {
        createBooking();
        SetTakeOwnership.setTakeOwnership(bookingTransportId);

        List<BookingTransportId> bookingToDo = GetBooking.getAllBookingToDoForTheDay(TestDataValue.BOOKING_DATE);

        for (BookingTransportId id: bookingToDo) {
            assertNotEquals(TestDataValue.NOT_EQUALS_VALUE, id.getId(), bookingTransportId);
        }

        DeleteBookingTransport.deleteBookingTransport(bookingTransportId);
        deleteAllTestDigitalTwin();
    }

    @Test
    public void deleteBookingTransport() {
        createBooking();
        DeleteBookingTransport.deleteBookingTransport(bookingTransportId);
        try {
            Client.getClient().getDigitalTwin(bookingTransportId.getId(), BasicDigitalTwin.class);
        } catch (Exception ex) {
            assertEquals(TestDataValue.NOT_EXIST, ex.getClass(), ErrorResponseException.class);
        }

        deleteAllTestDigitalTwin();
    }
}
