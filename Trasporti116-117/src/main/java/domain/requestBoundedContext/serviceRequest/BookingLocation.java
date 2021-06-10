/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.requestBoundedContext.serviceRequest;

import domain.*;

public class BookingLocation {
    private Address address;
    private HouseNumber houseNumber;
    private City city;
    private District district;
    private PostalCode postalCode;

    public BookingLocation(Address address, HouseNumber houseNumber, City city, District district, PostalCode postalCode) {
        this.address = address;
        this.houseNumber = houseNumber;
        this.city = city;
        this.district = district;
        this.postalCode = postalCode;
    }

}
