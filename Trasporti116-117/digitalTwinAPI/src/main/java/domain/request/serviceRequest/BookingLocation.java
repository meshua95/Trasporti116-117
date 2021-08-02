/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.request.serviceRequest;

import domain.PostalCode;
import domain.Address;
import domain.City;
import domain.District;
import domain.HouseNumber;

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
     * @param a location's address
     * @param hN location's house number
     * @param c location's city
     * @param d location's district
     * @param pC location's postal code
     *
     */
    public BookingLocation(final Address a, final HouseNumber hN, final City c, final District d, final PostalCode pC) {
        this.address = a;
        this.houseNumber = hN;
        this.city = c;
        this.district = d;
        this.postalCode = pC;
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
