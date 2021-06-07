/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.requestBoundedContext.serviceRequest;

import domain.patientBoundedContext.PatientResidence;

public class Route {

    private BookingLocation departure;
    private BookingLocation destination;

    public Route(BookingLocation departure, BookingLocation destination) {
        this.departure = departure;
        this.destination = destination;
    }
}
