/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.patient;

/**
 * Represents the patient's autonomy
 */
public enum Autonomy {
    /**
     * Patient is autonomous
     */
    AUTONOMOUS(1),
    /**
     * Patient is partially autonomous
     */
    PARTIALLY_AUTONOMOUS(2),
    /**
     * Patient isn't autonomous
     */
    NOT_AUTONOMOUS(3);

    private final int value;

    /**
     * Patient's autonomy
     *
     * @param v Patient's autonomy
     */
    Autonomy(final int v) {
        this.value = v;
    }

    /**
     * @return patient's autonomy
     */
    public int getValue() {
        return this.value;
    }

}
