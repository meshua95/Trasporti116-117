/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package utils.errorCode;

/**
 * Possibles status code when delete digital twin
 */
public enum DeleteDigitalTwinStatusCode {
    /**
     * Status code delated
     */
    DELETED (1),

    /**
     * Status code when relation existing
     */
    RELATION_EXISTING(2);

    private final int value;

    /**
     * Digital twin status code creation
     *
     * @param value status code
     */
    DeleteDigitalTwinStatusCode(int value) {
        this.value = value;
    }

    /**
     * @return value of status code
     */
    public int getValue() {
        return value;
    }
}
