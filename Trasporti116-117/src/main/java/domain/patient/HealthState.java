/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.patient;

public class HealthState {
    String description;

    public HealthState(String description) {
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }
}

