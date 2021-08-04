/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package digitaltwinsapi;

import domain.patient.PatientFiscalCode;

/**
 * Contains delete patient digital twin API
 */
public final class DeletePatient {
    private DeletePatient() { }

    /**
     * Delete a patient digital twin
     *
     * @param  idPatient id of the patient to be canceled
     */
    public static void deletePatient(final PatientFiscalCode idPatient) {
        Client.getClient().deleteDigitalTwin(idPatient.getFiscalCode());
    }
}
