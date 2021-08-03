/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.transport;

/**
 * Class that represents the transport identifier
 */
public class TransportId {
    private final String id;

    /**
     * Create transport ID
     *
     * @param transId transport id
     */
    public TransportId(final String transId) {
        this.id = transId;
    }

    /**
     * @return transport identifier
     */
    public String getId() {
        return id;
    }
}
