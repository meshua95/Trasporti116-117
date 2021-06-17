/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.patient;


import java.time.LocalDate;

public class PatientPersonalData {
    private final String name;
    private final String surname;
    private final LocalDate birthDate;
    private final PatientResidence residence;

    public PatientPersonalData(String name, String surname, LocalDate birthDate, PatientResidence residence) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.residence = residence;
    }
}
