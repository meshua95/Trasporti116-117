/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.patient;

public class PatientFiscalCode {

    private final String fiscalCode;

    public PatientFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public String getFiscalCode() {
        return this.fiscalCode;
    }
}

