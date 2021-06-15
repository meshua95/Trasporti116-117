/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.transport.operator;

import domain.*;

public class OperatorResidence {

    private Address address;
    private HouseNumber houseNumber;
    private City city;
    private District district;
    private PostalCode postalCode;

    public OperatorResidence(Address address, HouseNumber operatorHouseNumber, City city, District district, PostalCode operatorPostalCode) {
        this.address = address;
        this.houseNumber = operatorHouseNumber;
        this.city = city;
        this.district = district;
        this.postalCode = operatorPostalCode;
    }

}
