/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.transportBoundedContext;

public class OperatorCity {
    private String city;

    public OperatorCity(String name){
        this.city = name;
    }

    public String getCityName(){
        return this.city;
    }
}
