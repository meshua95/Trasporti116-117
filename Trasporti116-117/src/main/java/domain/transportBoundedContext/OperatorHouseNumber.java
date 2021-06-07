/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.transportBoundedContext;

public class OperatorHouseNumber {
    private final String houseNumber;

    public OperatorHouseNumber(String houseNumber){
        this.houseNumber = houseNumber;
    }

    public String getHouseNumber(){
        return this.houseNumber;
    }
}
