/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.trasporto;

import model.Route;
import model.TransportState;

public class Transport {
    private Route route;
    private TransportState stato;

    public Transport(Route route, TransportState stato) {
        this.route = route;
        this.stato = stato;
    }

    public Route getItinerario() {
        return route;
    }
}
