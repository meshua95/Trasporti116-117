/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.request.serviceRequest;

import domain.DTDLAddress;
import domain.DTDLCity;
import domain.DTDLHouseNumber;
import domain.DTDLDistrict;
import domain.DTDLPostalCode;

/**
 * Represents location for a booking
 */
public class BookingLocation {
    private final DTDLAddress address;
    private final DTDLHouseNumber houseNumber;
    private final DTDLCity city;
    private final DTDLDistrict district;
    private final DTDLPostalCode postalCode;

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
    public BookingLocation(final DTDLAddress a, final DTDLHouseNumber hN, final DTDLCity c, final DTDLDistrict d, final DTDLPostalCode pC) {
        this.address = a;
        this.houseNumber = hN;
        this.city = c;
        this.district = d;
        this.postalCode = pC;
    }

    /**
     * @return location's address
     * */
    public DTDLAddress getAddress() {
        return address;
    }

    /**
     * @return location's house number
     * */
    public DTDLHouseNumber getHouseNumber() {
        return houseNumber;
    }

    /**
     * @return location's city
     * */
    public DTDLCity getCity() {
        return city;
    }

    /**
     * @return location's district
     * */
    public DTDLDistrict getDistrict() {
        return district;
    }

    /**
     * @return location's postal code
     * */
    public DTDLPostalCode getPostalCode() {
        return postalCode;
    }
}
