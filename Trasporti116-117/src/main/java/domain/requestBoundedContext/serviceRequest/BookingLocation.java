/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.requestBoundedContext.serviceRequest;

public class BookingLocation {
    private BookingAddress address;
    private BookingHouseNumber houseNumber;
    private BookingCity city;
    private BookingDistrict district;
    private BookingPostalCode postalCode;

    public BookingLocation(BookingAddress address, BookingHouseNumber houseNumber, BookingCity city, BookingDistrict district, BookingPostalCode postalCode) {
        this.address = address;
        this.houseNumber = houseNumber;
        this.city = city;
        this.district = district;
        this.postalCode = postalCode;
    }

}
