/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.transport;

public class TransportRoute {

    private final TransportLocation departure;
    private final TransportLocation destination;

    public TransportRoute(TransportLocation departure, TransportLocation destination) {
        this.departure = departure;
        this.destination = destination;
    }
}
