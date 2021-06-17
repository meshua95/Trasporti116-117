/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.transport.operator;


import java.time.LocalDate;

public class OperatorPersonalData {
    private final String name;
    private final String surname;
    private final LocalDate birthDate;
    private final OperatorResidence residence;

    public OperatorPersonalData(String name, String surname, LocalDate birthDate, OperatorResidence residence) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.residence = residence;
    }
}
