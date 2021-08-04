/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.patient;

/**
 * Represents the patient's health state
 */
public class HealthState {
    private final String description;

    /**
     * Patient's health state
     *
     * @param d description of health state
     */
    public HealthState(final String d) {
        this.description = d;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }
}

