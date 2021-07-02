/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

import digitalTwinsAPI.GetAmbulance;
import utils.errorCode.QueryTimeOutException;
import viewCallCenter.MainAppCallCenter;

/**
 * Main class
 */
public class Main {
    /**
     * Main
     */
    public static void main(String... arg) {

        try {
            GetAmbulance.getAllAmbulanceIdTwins();
        } catch (QueryTimeOutException e) {
            e.printStackTrace();
        }
        //MainAppCallCenter.main(arg);
    }

}
