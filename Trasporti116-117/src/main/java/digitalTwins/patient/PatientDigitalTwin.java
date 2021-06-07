/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package digitalTwins.patient;

import com.azure.core.http.rest.PagedIterable;
import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicDigitalTwinMetadata;
import com.azure.digitaltwins.core.BasicRelationship;
import digitalTwins.Client;
import domain.patientBoundedContext.*;
import utils.Constants;

import java.util.ArrayList;

public class PatientDigitalTwin {

    public static void createPatient(PatientFiscalCode patientId, PatientPersonalData patientPersonalData, HealthState healthState, Autonomy autonomy){
        BasicDigitalTwin pazienteDT = new BasicDigitalTwin(patientId.getFiscalCode())
                .setMetadata(
                        new BasicDigitalTwinMetadata().setModelId(Constants.PATIENT_MODEL_ID)
                )
                .addToContents("personalData", patientPersonalData)
                .addToContents("healthState", healthState)
                .addToContents("autonomy", autonomy.getValue());

        BasicDigitalTwin basicTwinResponse = Client.getClient().createOrReplaceDigitalTwin(patientId.getFiscalCode(), pazienteDT, BasicDigitalTwin.class);
        System.out.println(basicTwinResponse.getId());
    }

    public static void deletePatient(PatientFiscalCode idPatient) {
        Client.getClient().listRelationships(idPatient.getFiscalCode(), BasicRelationship.class)
                .forEach(rel -> Client.getClient().deleteRelationship(idPatient.getFiscalCode(), rel.getId()));
        Client.getClient().deleteDigitalTwin(idPatient.getFiscalCode());
    }

    public static ArrayList<PatientFiscalCode> getAllPatientId(){
        ArrayList<PatientFiscalCode> patientsIds = new ArrayList<>();
        String query = "SELECT $dtId FROM DIGITALTWINS WHERE IS_OF_MODEL('"+ Constants.PATIENT_MODEL_ID + "')";
        PagedIterable<BasicDigitalTwin> pageableResponse = Client.getClient().query(query, BasicDigitalTwin.class);
        pageableResponse.forEach(r-> patientsIds.add(new PatientFiscalCode(r.getId())));
        return patientsIds;
    }
}
