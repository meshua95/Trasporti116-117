/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.transport;

/**
 * Class that represents the transport identifier
 */
public class TransportId {
    private final String transportId;

    /**
     * Create transport ID
     *
     * @param transportId transport identifier
     */
    public TransportId(String transportId) {
        this.transportId = transportId;
    }

    /**
     * @return transport identifier
     */
    public String getId() {
        return transportId;
    }
}
