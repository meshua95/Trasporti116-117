/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.transportBoundedContext;

import domain.requestBoundedContext.serviceRequest.BookingLocation;

public class TransportRoute {

    private TransportLocation departure;
    private TransportLocation destination;

    public TransportRoute(TransportLocation departure, TransportLocation destination) {
        this.departure = departure;
        this.destination = destination;
    }
}
