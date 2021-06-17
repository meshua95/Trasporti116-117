/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.transport;

import domain.*;

public class TransportLocation {
    private final Address address;
    private final HouseNumber houseNumber;
    private final City city;
    private final District district;
    private final PostalCode postalCode;

    public TransportLocation(Address address, HouseNumber houseNumber, City city, District district, PostalCode postalCode) {
        this.address = address;
        this.houseNumber = houseNumber;
        this.city = city;
        this.district = district;
        this.postalCode = postalCode;
    }

}
