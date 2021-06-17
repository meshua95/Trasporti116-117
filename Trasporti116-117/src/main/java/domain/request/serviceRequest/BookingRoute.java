/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.request.serviceRequest;

public class BookingRoute {

    private final BookingLocation departure;
    private final BookingLocation destination;

    public BookingRoute(BookingLocation departure, BookingLocation destination) {
        this.departure = departure;
        this.destination = destination;
    }
}
