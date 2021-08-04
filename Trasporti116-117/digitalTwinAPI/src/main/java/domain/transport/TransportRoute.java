/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.transport;

/**
 * Class that represent the transport route
 */
public class TransportRoute {

    private final TransportLocation departure;
    private final TransportLocation destination;

    /**
     * Create transport route
     *
     * @param dep location of departure
     * @param dest location of destination
     */
    public TransportRoute(final TransportLocation dep, final TransportLocation dest) {
        this.departure = dep;
        this.destination = dest;
    }

    /**
     * @return departure location
     */
    public TransportLocation getDeparture() {
        return this.departure;
    }

    /**
     * @return destination location
     */
    public TransportLocation getDestination() {
        return this.destination;
    }
}
