/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.request.serviceRequest;

/**
 * Represents a booking trasport id
 * */
public class BookingTransportId {
    private final String bookingTransportId;

    /**
     * Booking trasport id
     *
     * @param id booking trasport id
     * */
    public BookingTransportId(final String id) {
        this.bookingTransportId = id;
    }

    /**
     * @return booking trasport id
     * */
    public String getId() {
        return bookingTransportId;
    }
}
