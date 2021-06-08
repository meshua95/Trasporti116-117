/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicRelationship;
import com.azure.digitaltwins.core.implementation.models.ErrorResponseException;
import digitalTwins.Client;
import digitalTwins.booking.BookingDigitalTwin;
import digitalTwins.request.ServiceRequestDigitalTwin;
import domain.*;
import domain.patientBoundedContext.*;
import domain.requestBoundedContext.serviceRequest.*;
import digitalTwins.patient.PatientDigitalTwin;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class DtBookingTransport {
    private final PatientFiscalCode patientId = new PatientFiscalCode("paziente0");
    private final LocalDateTime dateTime = LocalDateTime.of(2021,07,05,18,00);
    private final BookingTransportId bookingTransportId = BookingDigitalTwin.generateBookingTransportId(patientId, dateTime);
    private final LocalDateTime dateTimeServiceReq = LocalDateTime.of(2021, 6, 10, 10,30);
    private final ServiceRequestId serviceReqId = ServiceRequestDigitalTwin.generateServiceRequestId(dateTimeServiceReq);

    @BeforeClass
    public static void createConnection(){
        Client.getClient();
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

    public void createServiceRequest(){
        ServiceRequestDigitalTwin.createServiceRequest(dateTimeServiceReq);
    }

    private void createBooking(){
        createPatient();
        createServiceRequest();

        BookingDigitalTwin.createBookingTransport(
                dateTime,
                new BookingRoute(
                        new BookingLocation(new Address("IV Settembre"),new HouseNumber("13B"),new City("Cesena"), new District("FC"), new PostalCode(47521)),
                        new BookingLocation(new Address("corso cavour"),new HouseNumber("189C"),new City("Cesena"), new District("FC"), new PostalCode(47521))),
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
