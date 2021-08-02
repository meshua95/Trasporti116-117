/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicRelationship;
import com.azure.digitaltwins.core.implementation.models.ErrorResponseException;
import digitaltwinsapi.*;
import domain.patient.*;
import domain.request.serviceRequest.*;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.errorCode.QueryTimeOutException;

import java.util.List;

import static org.junit.Assert.*;

public class DtBookingTransport {
    private final PatientFiscalCode patientId = new PatientFiscalCode(TestDataValue.PATIENT_FISCAL_CODE);
    final BookingTransportId bookingTransportId = GenerateId.generateBookingTransportId(patientId, TestDataValue.BOOKING_DATE);
    private final ServiceRequestId serviceReqId = GenerateId.generateServiceRequestId(TestDataValue.SERVICE_REQUEST_DATE);

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

        CreatePatient.createPatient(patientId, personalData, TestDataValue.HEALTH_STATE, Autonomy.NOT_AUTONOMOUS);
    }

    public void createServiceRequest(){
        CreateRequest.createServiceRequest(TestDataValue.SERVICE_REQUEST_DATE);
    }

    private void createBooking(){
        createPatient();
        createServiceRequest();

        CreateBookingTransport.createBookingTransport(
                TestDataValue.BOOKING_DATE,
                TestDataValue.BOOKING_ROUTE,
                patientId,
                serviceReqId);
    }

    private void deleteAllTestDigitalTwin(){
        DeletePatient.deletePatient(patientId);
    }

    @Test
    public void checkBookingCreation(){
        createBooking();

        assertEquals(Client.getClient().getDigitalTwin(bookingTransportId.getId(), BasicDigitalTwin.class).getClass(), BasicDigitalTwin.class);

        DeleteBookingTransport.deleteBookingTransport(bookingTransportId);
        deleteAllTestDigitalTwin();
    }

    @Test
    public void checkBookingPatientRelationship(){
        createBooking();
        assertEquals(Client.getClient().getRelationship(bookingTransportId.getId(), bookingTransportId.getId() + "to" + patientId.getFiscalCode(), BasicRelationship.class).getClass(), BasicRelationship.class);

        DeleteBookingTransport.deleteBookingTransport(bookingTransportId);
        deleteAllTestDigitalTwin();
    }

    @Test
    public void checkBookingServiceRequestRelationship(){
        createBooking();
        assertEquals(Client.getClient().getRelationship(bookingTransportId.getId(), bookingTransportId.getId() + "to" + serviceReqId.getserviceRequestId(), BasicRelationship.class).getClass(), BasicRelationship.class);

        DeleteBookingTransport.deleteBookingTransport(bookingTransportId);
        deleteAllTestDigitalTwin();
    }

    @Test
    public void checkBookingTakeOwnership() throws QueryTimeOutException {
        createBooking();
        SetTakeOwnership.setTakeOwnership(bookingTransportId);

        List<BookingTransportId> bookingToDo = GetBooking.getAllBookingToDoForTheDay(TestDataValue.BOOKING_DATE);

        for(BookingTransportId id: bookingToDo){
            assertNotEquals(id.getId(), bookingTransportId);
        }

        DeleteBookingTransport.deleteBookingTransport(bookingTransportId);
        deleteAllTestDigitalTwin();
    }

    @Test
    public void deleteBookingTransport(){
        createBooking();
        DeleteBookingTransport.deleteBookingTransport(bookingTransportId);
        try{
            Client.getClient().getDigitalTwin(bookingTransportId.getId(), BasicDigitalTwin.class);
        } catch (Exception ex){
            assertEquals(ex.getClass(), ErrorResponseException.class);
        }

        deleteAllTestDigitalTwin();
    }
}
