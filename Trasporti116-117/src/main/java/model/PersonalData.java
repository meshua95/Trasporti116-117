/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package model;


import java.time.LocalDate;

public class PersonalData {
    private String name;
    private String surname;
    private LocalDate birthDate;
    private Location residence;

    public PersonalData(String name, String surname, LocalDate birthDate, Location residence) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.residence = residence;
    }
}
