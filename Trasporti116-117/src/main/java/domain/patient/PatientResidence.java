/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.patient;

import domain.*;

public class PatientResidence {

    private final Address address;
    private final HouseNumber houseNumber;
    private final City city;
    private final District district;
    private final PostalCode postalCode;

    public PatientResidence(Address address, HouseNumber patientHouseNumber, City patientCity, District patientDistrict, PostalCode patientPostalCode) {
        this.address = address;
        this.houseNumber = patientHouseNumber;
        this.city = patientCity;
        this.district = patientDistrict;
        this.postalCode = patientPostalCode;
    }

}
