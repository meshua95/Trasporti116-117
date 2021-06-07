/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.patientBoundedContext;

public class PatientPostalCode {
    private int postalCode;

    public PatientPostalCode(int postalCode){
        this.postalCode = postalCode;
    }

    public int getPostalCode(){
        return this.postalCode;
    }
}
