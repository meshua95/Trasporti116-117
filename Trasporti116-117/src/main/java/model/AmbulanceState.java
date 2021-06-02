/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package model;

public enum AmbulanceState {
    FAULT(0),
    READY (1),
    USED(2),
    MAINTENACE(3);

    private int value;

    AmbulanceState(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return this.value;
    }
}
