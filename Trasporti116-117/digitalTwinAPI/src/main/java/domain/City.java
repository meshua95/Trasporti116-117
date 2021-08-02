/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain;

/**
 * Class that represents a city
 */
public class City {
    private final String city;

    /**
     * Create new City
     *
     * @param name city name
     */
    public City(final String name) {
        this.city = name;
    }

    /**
     * @return city name
     */
    public String getCity() {
        return this.city;
    }
}
