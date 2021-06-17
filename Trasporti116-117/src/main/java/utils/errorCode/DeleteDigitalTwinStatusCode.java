/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package utils.errorCode;

public enum DeleteDigitalTwinStatusCode {
    DELETED (1),
    RELATION_EXISTING(2);

    private int value;

    DeleteDigitalTwinStatusCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
