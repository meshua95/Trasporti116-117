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
     * @param departure location of departure
     * @param destination location of destination
     */
    public TransportRoute(TransportLocation departure, TransportLocation destination) {
        this.departure = departure;
        this.destination = destination;
    }

    /**
     * @return departure location
     */
    public TransportLocation getDeparture(){
        return this.departure;
    }

    /**
     * @return destination location
     */
    public TransportLocation getDestination(){
        return this.destination;
    }
}
