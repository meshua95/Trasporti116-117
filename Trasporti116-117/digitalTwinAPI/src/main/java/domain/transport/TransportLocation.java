/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.transport;
import domain.*;
/**
 * Class that represents a transport location
 */
public class TransportLocation {
    private final Address address;
    private final HouseNumber houseNumber;
    private final City city;
    private final District district;
    private final PostalCode postalCode;

    /**
     * Create transport location
     *
     * @param address location address
     * @param houseNumber location house number
     * @param city location city
     * @param district location district
     * @param postalCode location postal code
     */
    public TransportLocation(Address address, HouseNumber houseNumber, City city, District district, PostalCode postalCode) {
        this.address = address;
        this.houseNumber = houseNumber;
        this.city = city;
        this.district = district;
        this.postalCode = postalCode;
    }

    /**
     * Address getter
     *
     * @return address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @return house number
     */
    public HouseNumber getHouseNumber() {
        return houseNumber;
    }

    /**
     * @return city
     */
    public City getCity() {
        return city;
    }

    /**
     * @return district
     */
    public District getDistrict() {
        return district;
    }

    /**
     * @return postal code
     */
    public PostalCode getPostalCode() {
        return postalCode;
    }
}
