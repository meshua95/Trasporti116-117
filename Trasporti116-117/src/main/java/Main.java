/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

import digitalTwins.Client;
import domain.paziente.PazienteDigitalTwin;
import view.MainApp;

public class Main {
    public static void main(String... arg) {
        System.out.println(PazienteDigitalTwin.getAllPatientId());
        Client.getClient().listRelationships("ambulanza4",String.class).forEach(System.out::println);
        MainApp.main(arg);
    }

}