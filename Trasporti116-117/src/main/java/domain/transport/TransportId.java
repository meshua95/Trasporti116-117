/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.transport;

public class TransportId {
    private String transportId;

    public TransportId(String transportId) {
        this.transportId = transportId;
    }

    public String getId() {
        return transportId;
    }
}