/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.trasporto;

import model.Itinerario;
import model.StatoTrasporto;

public class Trasporto {
    private Itinerario itinerario;
    private StatoTrasporto stato;

    public Trasporto(Itinerario itinerario, StatoTrasporto stato) {
        this.itinerario = itinerario;
        this.stato = stato;
    }

    public Itinerario getItinerario() {
        return itinerario;
    }
}
