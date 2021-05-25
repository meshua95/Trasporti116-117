/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.trasporto;

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
