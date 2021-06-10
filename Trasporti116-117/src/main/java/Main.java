/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */


import digitalTwins.booking.BookingDigitalTwin;
import digitalTwins.transport.TransportDigitalTwin;
import domain.ambulanceBoundedContext.AmbulanceId;
import domain.requestBoundedContext.serviceRequest.BookingTransportId;
import domain.transportBoundedContext.OperatorId;
import domain.transportBoundedContext.TransportId;
import viewAmbulanceTablet.MainAppAmbulanceTablet;
import viewCallCenter.MainAppCallCenter;

public class Main {
    private static final String AMBULANCE_TABLET = "ambulanceTablet";
    private static final String CALL_CENTER  = "callCenter";

    public static void main(String... arg) {
        TransportDigitalTwin.startTransport(new BookingTransportId("booking2021-06-10_16-0_fdateafsf"), new AmbulanceId(1), new OperatorId("fghkku"));
        /*TransportDigitalTwin.setTransportEnded(new TransportId("2021-06-10_"));

        switch (arg[0]) {
            case AMBULANCE_TABLET -> MainAppAmbulanceTablet.main(arg);
            case CALL_CENTER -> MainAppCallCenter.main(arg);
        } */
    }
}
