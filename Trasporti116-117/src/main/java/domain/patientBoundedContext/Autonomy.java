/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.patientBoundedContext;

public enum Autonomy {
    AUTONOMOUS(1),
    PARTIALLY_AUTONOMOUS(2),
    NOT_AUTONOMOUS(3);

    private int value;

    Autonomy(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

}
