/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.transport.operator;

import domain.*;

public class OperatorResidence {

    private final Address address;
    private final HouseNumber houseNumber;
    private final City city;
    private final District district;
    private final PostalCode postalCode;

    public OperatorResidence(Address address, HouseNumber operatorHouseNumber, City city, District district, PostalCode operatorPostalCode) {
        this.address = address;
        this.houseNumber = operatorHouseNumber;
        this.city = city;
        this.district = district;
        this.postalCode = operatorPostalCode;
    }

}
