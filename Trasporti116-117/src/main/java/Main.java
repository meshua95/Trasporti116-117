/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

import digitalTwins.Client;
import domain.paziente.PatientDigitalTwin;
import view.MainApp;

public class Main {
    public static void main(String... arg) {
        Client.getClient();
        System.out.println(PatientDigitalTwin.getAllPatientId());
        MainApp.main(arg);
    }

}