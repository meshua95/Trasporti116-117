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
     * @param id transport id
     */
    public TransportId(final String id) {
        this.transportId = id;
    }

    /**
     * @return transport identifier
     */
    public String getId() {
        return transportId;
    }
}
