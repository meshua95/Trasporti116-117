/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.ambulanza;

public class Ambulanza {
    private StatoAmbulanza stato;

    public Ambulanza(StatoAmbulanza stato) {
        this.stato = stato;
    }

    public StatoAmbulanza getStato() {
        return stato;
    }
}
