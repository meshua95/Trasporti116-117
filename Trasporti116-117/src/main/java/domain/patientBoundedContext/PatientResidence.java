/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.patientBoundedContext;

public class PatientResidence {

    private PatientAddress address;
    private PatientHouseNumber houseNumber;
    private PatientCity city;
    private PatientDistrict district;
    private PatientPostalCode postalCode;

    public PatientResidence(PatientAddress address, PatientHouseNumber patientHouseNumber, PatientCity patientCity, PatientDistrict patientDistrict, PatientPostalCode patientPostalCode) {
        this.address = address;
        this.houseNumber = patientHouseNumber;
        this.city = patientCity;
        this.district = patientDistrict;
        this.postalCode = patientPostalCode;
    }

}
