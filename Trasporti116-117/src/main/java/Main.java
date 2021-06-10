/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

import digitalTwins.booking.BookingDigitalTwin;
import domain.patientBoundedContext.PatientFiscalCode;
import domain.requestBoundedContext.serviceRequest.BookingTransportId;
import viewAmbulanceTablet.MainAppAmbulanceTablet;
import viewCallCenter.MainAppCallCenter;

public class Main {

    private static final String AMBULANCE_TABLET = "ambulanceTablet";
    private static final String CALL_CENTER  = "callCenter";

    public static void main(String... arg) {
        /*System.out.println(BookingDigitalTwin.getPatientIdByBookingId(new BookingTransportId("booking2021-06-10_21-2_patientTest")));
        BookingDigitalTwin.getPatientIdByBookingId(new BookingTransportId("booking2021-06-10_21-9_patientTest")).doOnNext(n->{
            System.out.println(new PatientFiscalCode(n.get("$dtId").toString()).getFiscalCode());
        });*/
        switch (arg[0]) {
            case AMBULANCE_TABLET -> MainAppAmbulanceTablet.main(arg);
            case CALL_CENTER -> MainAppCallCenter.main(arg);
        }
    }
}
