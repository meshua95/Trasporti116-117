/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain;

/**
 * Class that represents postal code number
 */
public class PostalCode {
    private final int postalCode;

    /**
     * Create a new postal code number
     *
     * @param postalCode postal code number
     */
    public PostalCode(int postalCode){
        this.postalCode = postalCode;
    }

    /**
     * @return postal code number
     */
    public int getPostalCode(){
        return this.postalCode;
    }
}