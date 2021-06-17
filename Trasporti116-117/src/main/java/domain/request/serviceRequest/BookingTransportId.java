/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.request.serviceRequest;

public class BookingTransportId {
    private final String bookingTransportId;

    public BookingTransportId(String bookingTransportId) {
        this.bookingTransportId = bookingTransportId;
    }

    public String getId() {
        return bookingTransportId;
    }
}
