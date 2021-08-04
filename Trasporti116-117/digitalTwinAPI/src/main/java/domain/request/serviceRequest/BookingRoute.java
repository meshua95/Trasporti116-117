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
     * @param dep departure location
     * @param dest destination location
     * */
    public BookingRoute(final BookingLocation dep, final BookingLocation dest) {
        this.departure = dep;
        this.destination = dest;
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
