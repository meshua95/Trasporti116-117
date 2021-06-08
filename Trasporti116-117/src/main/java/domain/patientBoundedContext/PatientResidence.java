/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.patientBoundedContext;

import domain.*;

public class PatientResidence {

    private Address address;
    private HouseNumber houseNumber;
    private City city;
    private District district;
    private PostalCode postalCode;

    public PatientResidence(Address address, HouseNumber patientHouseNumber, City patientCity, District patientDistrict, PostalCode patientPostalCode) {
        this.address = address;
        this.houseNumber = patientHouseNumber;
        this.city = patientCity;
        this.district = patientDistrict;
        this.postalCode = patientPostalCode;
    }

}
