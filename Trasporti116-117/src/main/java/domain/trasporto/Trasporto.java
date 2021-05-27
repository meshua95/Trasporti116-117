/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.trasporto;

import model.Itinerario;
import model.TransportState;

public class Trasporto {
    private Itinerario itinerario;
    private TransportState stato;

    public Trasporto(Itinerario itinerario, TransportState stato) {
        this.itinerario = itinerario;
        this.stato = stato;
    }

    public Itinerario getItinerario() {
        return itinerario;
    }
}
