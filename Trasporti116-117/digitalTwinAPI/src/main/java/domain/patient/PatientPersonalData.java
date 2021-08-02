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
     * @param n name of patient
     * @param s surname of patient
     * @param bd birth date of patient
     * @param r residence of patient
     */
    public PatientPersonalData(final String n, final String s, final LocalDate bd, final PatientResidence r) {
        this.name = n;
        this.surname = s;
        this.birthDate = bd;
        this.residence = r;
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
