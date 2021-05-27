/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.ambulanza;

import model.AmbulanceState;

public class Ambulanza {
    private final AmbulanceState stato;

    public Ambulanza(AmbulanceState stato) {
        this.stato = stato;
    }

    public AmbulanceState getStato() {
        return stato;
    }
}
