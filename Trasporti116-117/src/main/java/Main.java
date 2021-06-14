/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

import digitalTwins.booking.BookingDigitalTwin;
import digitalTwins.patient.PatientDigitalTwin;
import digitalTwins.request.ServiceRequestDigitalTwin;
import domain.*;
import domain.patientBoundedContext.*;
import domain.requestBoundedContext.serviceRequest.BookingLocation;
import domain.requestBoundedContext.serviceRequest.BookingRoute;
import domain.requestBoundedContext.serviceRequest.BookingTransportId;
import domain.requestBoundedContext.serviceRequest.ServiceRequestId;
import org.apache.tools.ant.taskdefs.Local;
import viewAmbulanceTablet.MainAppAmbulanceTablet;
import viewCallCenter.MainAppCallCenter;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    private static final String AMBULANCE_TABLET = "ambulanceTablet";
    private static final String CALL_CENTER  = "callCenter";

    public static void main(String... arg) {
      /*  switch (arg[0]) {
            case AMBULANCE_TABLET -> MainAppAmbulanceTablet.main(arg);
            case CALL_CENTER -> MainAppCallCenter.main(arg);
        }

        */

        ServiceRequestId idRequest = new ServiceRequestId("serviceRequest_2021-06-14_9-55_");

        PatientPersonalData pd = new PatientPersonalData("Meshua", "Galassi", LocalDate.of(1995, 12,05), new PatientResidence(new Address("via selice"), new HouseNumber("2A"), new City("Massa Lombarda"), new District("RA"), new PostalCode(48024)));

        PatientDigitalTwin.createPatient(new PatientFiscalCode("GLSMSH95T45E730V"), pd, new HealthState("buono"), Autonomy.AUTONOMOUS);
        /*BookingRoute bookingRoute = new BookingRoute(new BookingLocation(new Address("IV Settembre"),new HouseNumber("13B"),new City("Cesena"), new District("FC"), new PostalCode(47521)),
                new BookingLocation(new Address("corso cavour"),new HouseNumber("189C"),new City("Cesena"), new District("FC"), new PostalCode(47521)));

        BookingTransportId bookingId = BookingDigitalTwin.createBookingTransport(LocalDateTime.now(), bookingRoute, )
*/

    }
}
