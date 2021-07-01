/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.patient;

/**
 * Represents the patient's fiscal code
 */
public class PatientFiscalCode {

    private final String fiscalCode;

    /**
     * Patient's fiscal code
     *
     * @param fiscalCode fiscal code
     */
    public PatientFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    /**
     * Return the patient's fiscal code
     *
     * @return patient fiscal code
     */
    public String getFiscalCode() {
        return this.fiscalCode;
    }
}

