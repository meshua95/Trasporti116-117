/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.requestBoundedContext.serviceRequest;

public class BookingDistrict {
    private String district;

    public BookingDistrict(String district){
        this.district = district;
    }

    public String getDistrict(){
        return this.district;
    }
}
