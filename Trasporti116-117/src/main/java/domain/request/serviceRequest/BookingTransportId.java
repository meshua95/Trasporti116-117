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
     * @param bookingTransportId booking trasport id
     * */
    public BookingTransportId(String bookingTransportId) {
        this.bookingTransportId = bookingTransportId;
    }

    /**
     * @return booking trasport id
     * */
    public String getId() {
        return bookingTransportId;
    }
}
