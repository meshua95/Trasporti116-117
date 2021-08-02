/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.transport.operator;

import domain.PostalCode;
import domain.Address;
import domain.City;
import domain.District;
import domain.HouseNumber;

/**
 * Class that represents the operator residence
 */
public class OperatorResidence {

    private final Address address;
    private final HouseNumber houseNumber;
    private final City city;
    private final District district;
    private final PostalCode postalCode;

    /**
     * Create operator residence
     * @param a operator residence address
     * @param hn operator residence house number
     * @param c operator residence city
     * @param d operator residence district
     * @param pc operator residence postal code
     */
    public OperatorResidence(final Address a, final HouseNumber hn, final City c, final District d, final PostalCode pc) {
        this.address = a;
        this.houseNumber = hn;
        this.city = c;
        this.district = d;
        this.postalCode = pc;
    }

    /**
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
