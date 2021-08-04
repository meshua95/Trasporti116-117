/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.request.serviceRequest;

/**
 * Represents a booking trasport id
 * */
public class BookingTransportId {
    private final String id;

    /**
     * Booking trasport id
     *
     * @param value booking trasport id
     * */
    public BookingTransportId(final String value) {
        this.id = value;
    }

    /**
     * @return booking trasport id
     * */
    public String getId() {
        return id;
    }
}
