/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

import digitalTwins.Client;
import digitalTwins.DigitalTwinEraser;
import digitalTwins.DigitalTwinQuery;
import view.MainApp;

public class Main {
    public static void main(String... arg) {
        Client.createClient();
        DigitalTwinEraser.deleteTwin("ambulanza4");
       // MainApp.main(arg);
    }

}