/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */
package digitaltwinsapi;

import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicDigitalTwinMetadata;
import domain.patient.Autonomy;
import domain.patient.HealthState;
import domain.patient.PatientFiscalCode;
import domain.patient.PatientPersonalData;
import utils.Constants;

/**
 * Contains create patient digital twin API
 */
public final class CreatePatient {
    private CreatePatient() { }

    /**
     * Create a patient digital twin
     *
     * @param patientId fiscal code of patient
     * @param patientPersonalData personal data of patient
     * @param healthState health state of a patient
     * @param autonomy autonomy of a patient
     *
     * @return id of the ambulance created
     */
    public static String createPatient(final PatientFiscalCode patientId, final PatientPersonalData patientPersonalData, final HealthState healthState, final Autonomy autonomy) {
        BasicDigitalTwin pazienteDT = new BasicDigitalTwin(patientId.getFiscalCode())
                .setMetadata(
                        new BasicDigitalTwinMetadata().setModelId(Constants.PATIENT_MODEL_ID)
                )
                .addToContents("personalData", patientPersonalData)
                .addToContents("healthState", healthState)
                .addToContents("autonomy", autonomy.getValue());

        BasicDigitalTwin basicTwinResponse = Client.getClient().createOrReplaceDigitalTwin(patientId.getFiscalCode(), pazienteDT, BasicDigitalTwin.class);
        System.out.println(basicTwinResponse.getId());
        return basicTwinResponse.getId();
    }
}
