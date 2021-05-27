/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package model;

public enum StatoAmbulanza {
    GUASTA(1),
    PRONTA(2),
    IN_USO(3),
    IN_MANUTENZIONE(4);

    private int value;

    StatoAmbulanza(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return this.value;
    }
}
