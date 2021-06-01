/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

import digitalTwins.Client;
import domain.ambulanza.AmbulanceDigitalTwin;
import domain.ambulanza.AmbulanceId;
import domain.trasporto.TransportDigitalTwin;

public class Main {
    public static void main(String... arg) {
        // Client.getClient().listRelationships("ambulanza4", String.class).forEach(System.out::println);
        // Client.getClient().listRelationships("Trasporto2", String.class).forEach(System.out::println);
        TransportDigitalTwin.getTransportOfAmbulance(new AmbulanceId("ambulanza4")).forEach(System.out::println);
        //  MainApp.main(arg);
    }

}