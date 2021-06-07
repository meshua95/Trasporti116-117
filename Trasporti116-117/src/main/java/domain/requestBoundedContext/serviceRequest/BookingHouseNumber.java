/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.requestBoundedContext.serviceRequest;

public class BookingHouseNumber {
    private final String houseNumber;

    public BookingHouseNumber(String houseNumber){
        this.houseNumber = houseNumber;
    }

    public String getHouseNumber(){
        return this.houseNumber;
    }
}
