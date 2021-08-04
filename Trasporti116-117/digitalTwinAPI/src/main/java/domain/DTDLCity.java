/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain;

/**
 * Class that represents a city
 */
public class DTDLCity {
    private final String city;

    /**
     * Create new DTDLCity
     *
     * @param name city name
     */
    public DTDLCity(final String name) {
        this.city = name;
    }

    /**
     * @return city name
     */
    public String getCity() {
        return this.city;
    }
}
