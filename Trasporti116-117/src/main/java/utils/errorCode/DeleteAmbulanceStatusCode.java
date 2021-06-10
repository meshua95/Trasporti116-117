/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package utils.errorCode;

public enum DeleteAmbulanceStatusCode {
    DELETED (1),
    TRANSPORT_RELATION_EXISTING (2);

    private int value;

    DeleteAmbulanceStatusCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
