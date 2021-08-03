/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.transport;

import domain.DTDLAddress;
import domain.DTDLCity;
import domain.DTDLHouseNumber;
import domain.DTDLDistrict;
import domain.DTDLPostalCode;

/**
 * Class that represents a transport location
 */
public class TransportLocation {
    private final DTDLAddress address;
    private final DTDLHouseNumber houseNumber;
    private final DTDLCity city;
    private final DTDLDistrict district;
    private final DTDLPostalCode postalCode;

    /**
     * Create transport location
     *
     * @param a location address
     * @param hN location house number
     * @param c location city
     * @param d location district
     * @param pC location postal code
     */
    public TransportLocation(final DTDLAddress a, final DTDLHouseNumber hN, final DTDLCity c, final DTDLDistrict d, final DTDLPostalCode pC) {
        this.address = a;
        this.houseNumber = hN;
        this.city = c;
        this.district = d;
        this.postalCode = pC;
    }

    /**
     * DTDLAddress getter
     *
     * @return address
     */
    public DTDLAddress getAddress() {
        return address;
    }

    /**
     * @return house number
     */
    public DTDLHouseNumber getHouseNumber() {
        return houseNumber;
    }

    /**
     * @return city
     */
    public DTDLCity getCity() {
        return city;
    }

    /**
     * @return district
     */
    public DTDLDistrict getDistrict() {
        return district;
    }

    /**
     * @return postal code
     */
    public DTDLPostalCode getPostalCode() {
        return postalCode;
    }
}
