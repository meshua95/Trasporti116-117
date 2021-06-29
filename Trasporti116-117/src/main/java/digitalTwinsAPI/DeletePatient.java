/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package digitalTwinsAPI;

import com.azure.digitaltwins.core.BasicRelationship;
import domain.patient.PatientFiscalCode;

public class DeletePatient {
    private DeletePatient(){}

    /**
     * Delete a patient digital twin
     *
     * @param  idPatient id of the patient to be canceled
     */
    public static void deletePatient(PatientFiscalCode idPatient) {
        Client.getClient().deleteDigitalTwin(idPatient.getFiscalCode());
    }
}
