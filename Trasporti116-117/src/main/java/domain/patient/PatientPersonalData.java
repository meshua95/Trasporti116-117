/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.patient;

import java.time.LocalDate;

/**
 * Represents the patient's personal data
 */
public class PatientPersonalData {
    private final String name;
    private final String surname;
    private final LocalDate birthDate;
    private final PatientResidence residence;

    /**
     * Patient's personal data
     *
     * @param name name of patient
     * @param surname surname of patient
     * @param birthDate birth date of patient
     * @param residence residence of patient
     */
    public PatientPersonalData(String name, String surname, LocalDate birthDate, PatientResidence residence) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.residence = residence;
    }

    /**
     * @return name of patient
     */
    public String getName() {
        return name;
    }

    /**
     * @return surname of patient
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @return birth date of patient
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * @return residence of patient
     */
    public PatientResidence getResidence() {
        return residence;
    }
}
