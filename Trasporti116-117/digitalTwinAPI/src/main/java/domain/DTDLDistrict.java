/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain;

/**
 * Class that represents a district
 */
public class DTDLDistrict {
    private final String district;

    /**
     * Create new district
     *
     * @param d address district
     */
    public DTDLDistrict(final String d) {
        this.district = d;
    }

    /**
     * @return district name
     */
    public String getDistrict() {
        return this.district;
    }
}
