/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.request.serviceRequest;

import domain.*;

/**
 * Represents location for a booking
 */
public class BookingLocation {
    private final Address address;
    private final HouseNumber houseNumber;
    private final City city;
    private final District district;
    private final PostalCode postalCode;

    /**
     * Location for a booking
     *
     * @param address location's address
     * @param houseNumber location's house number
     * @param city location's city
     * @param district location's district
     * @param postalCode location's postal code
     *
     */
    public BookingLocation(Address address, HouseNumber houseNumber, City city, District district, PostalCode postalCode) {
        this.address = address;
        this.houseNumber = houseNumber;
        this.city = city;
        this.district = district;
        this.postalCode = postalCode;
    }

    /**
     * @return location's address
     * */
    public Address getAddress() {
        return address;
    }

    /**
     * @return location's house number
     * */
    public HouseNumber getHouseNumber() {
        return houseNumber;
    }

    /**
     * @return location's city
     * */
    public City getCity() {
        return city;
    }

    /**
     * @return location's district
     * */
    public District getDistrict() {
        return district;
    }

    /**
     * @return location's postal code
     * */
    public PostalCode getPostalCode() {
        return postalCode;
    }
}
