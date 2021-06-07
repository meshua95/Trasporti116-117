/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.transportBoundedContext;


import java.time.LocalDate;

public class OperatorPersonalData {
    private String name;
    private String surname;
    private LocalDate birthDate;
    private OperatorResidence residence;

    public OperatorPersonalData(String name, String surname, LocalDate birthDate, OperatorResidence residence) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.residence = residence;
    }
}
