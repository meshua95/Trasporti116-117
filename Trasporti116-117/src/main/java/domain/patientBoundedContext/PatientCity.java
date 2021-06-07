/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.patientBoundedContext;

public class PatientCity {
    private String city;

    public PatientCity(String name){
        this.city = name;
    }

    public String getCityName(){
        return this.city;
    }
}
