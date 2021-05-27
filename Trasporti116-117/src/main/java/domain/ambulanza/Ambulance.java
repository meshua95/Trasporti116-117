/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.ambulanza;

import model.AmbulanceState;

public class Ambulance {
    private final AmbulanceState state;

    public Ambulance(AmbulanceState state) {
        this.state = state;
    }

    public AmbulanceState getState() {
        return state;
    }
}
