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
     * @param description description of health state
     */
    public HealthState(String description) {
        this.description = description;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }
}

