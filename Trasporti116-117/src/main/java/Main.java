/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

import digitalTwins.booking.BookingDigitalTwin;
import view.MainApp;

public class Main {
    public static void main(String... arg) {
        //MainApp.main(arg);
        BookingDigitalTwin.getAllBookingForToday();
    }
}