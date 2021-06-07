/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.requestBoundedContext.serviceRequest;

public class BookingPostalCode {
    private int postalCode;

    public BookingPostalCode(int postalCode){
        this.postalCode = postalCode;
    }

    public int getPostalCode(){
        return this.postalCode;
    }
}
