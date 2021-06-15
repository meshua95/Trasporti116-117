/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.request.serviceRequest;

public class BookingRoute {

    private BookingLocation departure;
    private BookingLocation destination;

    public BookingRoute(BookingLocation departure, BookingLocation destination) {
        this.departure = departure;
        this.destination = destination;
    }
}
