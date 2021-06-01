/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package model;

public enum AmbulanceState {
    FAULT(1),
    READY (2),
    USED(3),
    MAINTENACE(4);

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
