/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package model;

public class AmbulanceId {
    private final String ambulanceId;
    private final GpsId gpsAmbulanceId;

    public AmbulanceId(int ambulanceNumber) {
        this.ambulanceId = "ambulanza-" + ambulanceNumber;
        this.gpsAmbulanceId = new GpsId(ambulanceNumber);
    }

    public AmbulanceId(String ambulanceId) {
        this.ambulanceId = ambulanceId;
        int ambulanceNumber = getAmbulanceNumber();
        this.gpsAmbulanceId = new GpsId(ambulanceNumber);
    }

    public String getAmbulanceId() {
        return this.ambulanceId;
    }

    public String getGpsId(){
        return this.gpsAmbulanceId.getGpsId();
    }

    public int getAmbulanceNumber(){
        String[] ambulanceIdSplitted = ambulanceId.split("-");
        return Integer.parseInt(ambulanceIdSplitted[1]);
    }

    private static class GpsId{
        private final String gpsAmbulanceId;

        public GpsId(int ambulanceNumber){
            this.gpsAmbulanceId = "gps-" + ambulanceNumber;
        }

        public String getGpsId(){
            return this.gpsAmbulanceId;
        }
    }
}
