/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.patientBoundedContext;

public class PatientHouseNumber {
    private final String houseNumber;

    public PatientHouseNumber(String houseNumber){
        this.houseNumber = houseNumber;
    }

    public String getHouseNumber(){
        return this.houseNumber;
    }
}
