/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.patient;

import domain.*;

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
     * @param address patient's address
     * @param patientHouseNumber patient's house number
     * @param patientCity patient's city
     * @param patientDistrict patient's district
     * @param patientPostalCode patient's postal code
     *
     */
    public PatientResidence(Address address, HouseNumber patientHouseNumber, City patientCity, District patientDistrict, PostalCode patientPostalCode) {
        this.address = address;
        this.houseNumber = patientHouseNumber;
        this.city = patientCity;
        this.district = patientDistrict;
        this.postalCode = patientPostalCode;
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
