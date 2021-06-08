/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package utils.errorCode;

public enum DeleteBookingStatusCode {
    DELETED (1),
    TRANSPORT_RELATION_EXISTING (2);

    private int value;

    DeleteBookingStatusCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
