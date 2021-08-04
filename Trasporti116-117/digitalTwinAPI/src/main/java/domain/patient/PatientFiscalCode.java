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
     * @param fc fiscal code
     */
    public PatientFiscalCode(final String fc) {
        this.fiscalCode = fc;
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

