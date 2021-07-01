/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain;

/**
 * Class that represents an address
 */
public class Address {
    private final String address;

    /**
     * Create address
     *
     * @param address location address
     */
    public Address(String address){
        this.address = address;
    }

    /**
     * @return address string
     */
    public String getAddress(){
        return this.address;
    }
}

