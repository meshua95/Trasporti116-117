/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.requestBoundedContext.serviceRequest;

public class BookingCity {
    private String city;

    public BookingCity(String name){
        this.city = name;
    }

    public String getCityName(){
        return this.city;
    }
}
