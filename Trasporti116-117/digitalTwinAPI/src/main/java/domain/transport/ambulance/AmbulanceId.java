/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.transport.ambulance;

/**
 * Represents the id of an ambulance
 */
public class AmbulanceId {
    private final String ambulanceId;
    private final GpsId gpsAmbulanceId;

    /**
     * Create id of an ambulance by ambulance number
     *
     * @param ambulanceNumber ambulance number
     */
    public AmbulanceId(final int ambulanceNumber) {
        this.ambulanceId = "ambulanza-" + ambulanceNumber;
        this.gpsAmbulanceId = new GpsId(ambulanceNumber);
    }

    /**
     * Create id of an ambulance by ambulance id
     *
     * @param ambId ambulance id
     */
    public AmbulanceId(final String ambId) {
        this.ambulanceId = ambId;
        int ambulanceNumber = getAmbulanceNumber();
        this.gpsAmbulanceId = new GpsId(ambulanceNumber);
    }

    /**
     * @return ambulance id
     */
    public String getAmbulanceId() {
        return this.ambulanceId;
    }

    /**
     * @return gps id
     */
    public String getGpsId() {
        return this.gpsAmbulanceId.getGpsId();
    }

    /**
     * @return ambulance number
     */
    public int getAmbulanceNumber() {
        String[] ambulanceIdSplitted = ambulanceId.split("-");
        return Integer.parseInt(ambulanceIdSplitted[1]);
    }

    private static class GpsId {
        private final String gpsAmbulanceId;

         GpsId(final int ambulanceNumber) {
            this.gpsAmbulanceId = "gps-" + ambulanceNumber;
        }

         String getGpsId() {
            return this.gpsAmbulanceId;
        }
    }
}
