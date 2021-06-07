/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.transportBoundedContext;

public class OperatorResidence {

    private OperatorAddress address;
    private OperatorHouseNumber houseNumber;
    private OperatorCity city;
    private OperatorDistrict district;
    private OperatorPostalCode postalCode;

    public OperatorResidence(OperatorAddress address, OperatorHouseNumber operatorHouseNumber, OperatorCity city, OperatorDistrict district, OperatorPostalCode operatorPostalCode) {
        this.address = address;
        this.houseNumber = operatorHouseNumber;
        this.city = city;
        this.district = district;
        this.postalCode = operatorPostalCode;
    }

}
