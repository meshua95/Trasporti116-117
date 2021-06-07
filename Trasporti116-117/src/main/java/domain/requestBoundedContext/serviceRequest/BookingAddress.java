/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.requestBoundedContext.serviceRequest;

public class BookingAddress {
    private final String address;

    public BookingAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return this.address;
    }
}

