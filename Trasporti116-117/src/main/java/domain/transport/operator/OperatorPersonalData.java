/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.transport.operator;

import java.time.LocalDate;

/**
 * Class that represents the operator personal data
 */
public class OperatorPersonalData {
    private final String name;
    private final String surname;
    private final LocalDate birthDate;
    private final OperatorResidence residence;

    /**
     * Create operator personal data
     *
     * @param name operator name
     * @param surname operator surname
     * @param birthDate operator birth date
     * @param residence operator residence
     */
    public OperatorPersonalData(String name, String surname, LocalDate birthDate, OperatorResidence residence) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.residence = residence;
    }

    /**
     * @return operator name
     */
    public String getName() {
        return name;
    }

    /**
     * @return operator surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @return operator birth date
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * @return operator residence
     */
    public OperatorResidence getResidence() {
        return residence;
    }
}
