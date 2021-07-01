/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.request.serviceRequest;

/**
 * Represents a booking route
 * */
public class BookingRoute {

    private final BookingLocation departure;
    private final BookingLocation destination;

    /**
     * Booking route
     *
     * @param departure departure location
     * @param destination destination location
     * */
    public BookingRoute(BookingLocation departure, BookingLocation destination) {
        this.departure = departure;
        this.destination = destination;
    }

    /**
     * @return departure location
     */
    public BookingLocation getDeparture() {
        return departure;
    }

    /**
     * @return destination location
     */
    public BookingLocation getDestination() {
        return destination;
    }
}
