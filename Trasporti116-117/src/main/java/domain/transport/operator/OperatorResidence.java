/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.transport.operator;

import domain.*;

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
     * @param address operator residence address
     * @param operatorHouseNumber operator residence house number
     * @param city operator residence city
     * @param district operator residence district
     * @param operatorPostalCode operator residence postal code
     */
    public OperatorResidence(Address address, HouseNumber operatorHouseNumber, City city, District district, PostalCode operatorPostalCode) {
        this.address = address;
        this.houseNumber = operatorHouseNumber;
        this.city = city;
        this.district = district;
        this.postalCode = operatorPostalCode;
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
