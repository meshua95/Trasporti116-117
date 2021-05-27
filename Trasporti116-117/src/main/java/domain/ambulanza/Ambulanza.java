/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.ambulanza;

import model.StatoAmbulanza;

public class Ambulanza {
    private final StatoAmbulanza stato;

    public Ambulanza(StatoAmbulanza stato) {
        this.stato = stato;
    }

    public StatoAmbulanza getStato() {
        return stato;
    }
}
