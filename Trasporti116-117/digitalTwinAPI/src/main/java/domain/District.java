/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain;

/**
 * Class that represents a district
 */
public class District {
    private final String district;

    /**
     * Create new district
     *
     * @param district address district
     */
    public District(String district){
        this.district = district;
    }

    /**
     * @return district name
     */
    public String getDistrict(){
        return this.district;
    }
}
