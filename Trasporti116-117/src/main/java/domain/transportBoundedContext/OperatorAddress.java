/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.transportBoundedContext;

public class OperatorAddress {
    private final String address;

    public OperatorAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return this.address;
    }
}

