/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

import digitalTwins.Client;
import domain.ambulanza.AmbulanceId;
import domain.paziente.PatientDigitalTwin;
import domain.trasporto.TransportDigitalTwin;
import view.MainApp;
import view.dialog.TransportDialog;

public class Main {
    public static void main(String... arg) {
        //Client.getClient().listRelationships("ambulanza4", String.class).forEach(System.out::println);
     //   Client.getClient().listRelationships("Trasporto2", String.class).forEach(System.out::println);
        TransportDigitalTwin.getTransportofAmbulance(new AmbulanceId("ambulanza1")).forEach(System.out::println);
     //  MainApp.main(arg);
    }

}