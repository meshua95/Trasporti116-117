package domain.transport.ambulance;

/**
 * Represents the earth's coordinates
 * */
public class Coordinates {
    double longitude;
    double latitude;

    /**
     * Create the coordinates
     *
     * @param latitude latitude of coordinates
     * @param longitude longitude of coordinates
     * */
    public Coordinates(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    /**
     *
     * @return longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     *
     * @return latitude
     */
    public double getLatitude() {
        return latitude;
    }
}
