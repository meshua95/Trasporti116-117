/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.transportBoundedContext;

import domain.*;

public class TransportLocation {
    private Address address;
    private HouseNumber houseNumber;
    private City city;
    private District district;
    private PostalCode postalCode;

    public TransportLocation(Address address, HouseNumber houseNumber, City city, District district, PostalCode postalCode) {
        this.address = address;
        this.houseNumber = houseNumber;
        this.city = city;
        this.district = district;
        this.postalCode = postalCode;
    }

}
