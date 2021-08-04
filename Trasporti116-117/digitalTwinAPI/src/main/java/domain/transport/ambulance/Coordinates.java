package domain.transport.ambulance;

/**
 * Represents the earth's coordinates
 * */
public class Coordinates {
    private final double longitude;
    private final double latitude;

    /**
     * Create the coordinates
     *
     * @param lat latitude of coordinates
     * @param lon longitude of coordinates
     * */
    public Coordinates(final double lon, final double lat) {
        this.longitude = lon;
        this.latitude = lat;
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
