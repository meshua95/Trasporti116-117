/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

import digitalTwins.Client;
import view.MainApp;

public class Main {
    public static void main(String... arg) {
        Client.createClient();
        MainApp.main(arg);
    }

}