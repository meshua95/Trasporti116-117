/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

import com.azure.digitaltwins.core.BasicRelationship;
import digitalTwins.Client;
import domain.ambulanza.AmbulanceDigitalTwin;
import domain.trasporto.TransportDigitalTwin;
import model.AmbulanceId;
import view.MainApp;

import java.time.LocalDateTime;

public class Main {
    public static void main(String... arg) {
       //  Client.getClient().listRelationships("2021-05-05_18-0_CRGMHI12M21E730X", String.class).forEach(System.out::println);
        // Client.getClient().listRelationships("Trasporto2", String.class).forEach(System.out::println);
        //TransportDigitalTwin.getTransportOfAmbulance(new AmbulanceId("ambulanza4")).forEach(System.out::println);
    //    AmbulanceDigitalTwin.getGPSCoordinatesOfAmbulance(new AmbulanceId(2));
        MainApp.main(arg);
    }
}