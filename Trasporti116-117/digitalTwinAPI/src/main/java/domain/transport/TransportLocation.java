/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.transport;

import domain.PostalCode;
import domain.Address;
import domain.City;
import domain.District;
import domain.HouseNumber;

/**
 * Class that represents a transport location
 */
public class TransportLocation {
    private final Address address;
    private final HouseNumber houseNumber;
    private final City city;
    private final District district;
    private final PostalCode postalCode;

    /**
     * Create transport location
     *
     * @param a location address
     * @param hN location house number
     * @param c location city
     * @param d location district
     * @param pC location postal code
     */
    public TransportLocation(final Address a, final HouseNumber hN, final City c, final District d, final PostalCode pC) {
        this.address = a;
        this.houseNumber = hN;
        this.city = c;
        this.district = d;
        this.postalCode = pC;
    }

    /**
     * Address getter
     *
     * @return address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @return house number
     */
    public HouseNumber getHouseNumber() {
        return houseNumber;
    }

    /**
     * @return city
     */
    public City getCity() {
        return city;
    }

    /**
     * @return district
     */
    public District getDistrict() {
        return district;
    }

    /**
     * @return postal code
     */
    public PostalCode getPostalCode() {
        return postalCode;
    }
}
