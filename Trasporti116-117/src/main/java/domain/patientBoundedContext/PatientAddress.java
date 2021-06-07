/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.patientBoundedContext;

public class PatientAddress {
    private final String address;

    public PatientAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return this.address;
    }
}

