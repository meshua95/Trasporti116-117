/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.patientBoundedContext;


import java.time.LocalDate;

public class PatientPersonalData {
    private String name;
    private String surname;
    private LocalDate birthDate;
    private PatientResidence residence;

    public PatientPersonalData(String name, String surname, LocalDate birthDate, PatientResidence residence) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.residence = residence;
    }
}
