/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.requestBoundedContext.serviceRequest;

public class BookingTransportId {
    private String bookingTransportId;

    public BookingTransportId(String bookingTransportId) {
        this.bookingTransportId = bookingTransportId;
    }

    public String getId() {
        return bookingTransportId;
    }
}
