/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package model;

public enum TransportState {
    NOT_STARTED(1),
    IN_PROGRESS(2),
    ENDED(3),
    CANCELLED(0);

    private int value;

    TransportState(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return this.value;
    }
}
