/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.patient;

import domain.DTDLAddress;
import domain.DTDLCity;
import domain.DTDLHouseNumber;
import domain.DTDLDistrict;
import domain.DTDLPostalCode;

/**
 * Represents the patient's personal residence
 */
public class PatientResidence {
    private final DTDLAddress address;
    private final DTDLHouseNumber houseNumber;
    private final DTDLCity city;
    private final DTDLDistrict district;
    private final DTDLPostalCode postalCode;

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
    public PatientResidence(final DTDLAddress a, final DTDLHouseNumber hn, final DTDLCity c, final DTDLDistrict d, final DTDLPostalCode pc) {
        this.address = a;
        this.houseNumber = hn;
        this.city = c;
        this.district = d;
        this.postalCode = pc;
    }

    /**
     * @return patient's address
     */
    public DTDLAddress getAddress() {
        return address;
    }

    /**
     * @return patient's house number
     */
    public DTDLHouseNumber getHouseNumber() {
        return houseNumber;
    }

    /**
     * @return patient's city
     */
    public DTDLCity getCity() {
        return city;
    }

    /**
     * @return patient's district
     */
    public DTDLDistrict getDistrict() {
        return district;
    }

    /**
     * @return patient's postal code
     */
    public DTDLPostalCode getPostalCode() {
        return postalCode;
    }
}
