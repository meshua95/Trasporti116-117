/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.patient;

import domain.PostalCode;
import domain.Address;
import domain.City;
import domain.District;
import domain.HouseNumber;

/**
 * Represents the patient's personal residence
 */
public class PatientResidence {
    private final Address address;
    private final HouseNumber houseNumber;
    private final City city;
    private final District district;
    private final PostalCode postalCode;

    /**
     * Patient's residence
     *
     * @param a patient's address
     * @param hn patient's house number
     * @param c patient's city
     * @param d patient's district
     * @param pc patient's postal code
     *
     */
    public PatientResidence(final Address a, final HouseNumber hn, final City c, final District d, final PostalCode pc) {
        this.address = a;
        this.houseNumber = hn;
        this.city = c;
        this.district = d;
        this.postalCode = pc;
    }

    /**
     * @return patient's address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @return patient's house number
     */
    public HouseNumber getHouseNumber() {
        return houseNumber;
    }

    /**
     * @return patient's city
     */
    public City getCity() {
        return city;
    }

    /**
     * @return patient's district
     */
    public District getDistrict() {
        return district;
    }

    /**
     * @return patient's postal code
     */
    public PostalCode getPostalCode() {
        return postalCode;
    }
}
