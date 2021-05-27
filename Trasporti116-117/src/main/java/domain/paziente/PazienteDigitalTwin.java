/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.paziente;

import com.azure.core.http.rest.PagedIterable;
import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicDigitalTwinMetadata;
import com.azure.digitaltwins.core.BasicRelationship;
import digitalTwins.Client;
import model.*;
import utils.Constants;

import java.util.ArrayList;

public class PazienteDigitalTwin {

    public static void createPaziente(String dtId, PersonalData datiAnagraficiPaziente, StatoDiSalute statoDiSalute, Autonomy autonomy){
        BasicDigitalTwin pazienteDT = new BasicDigitalTwin(dtId)
                .setMetadata(
                        new BasicDigitalTwinMetadata().setModelId(Constants.PAZIENTE_ID)
                )
                .addToContents("datiAnagrafici", datiAnagraficiPaziente)
                .addToContents("statoDiSalute", statoDiSalute)
                .addToContents("autonomia", autonomy.getValue());

        BasicDigitalTwin basicTwinResponse = Client.getClient().createOrReplaceDigitalTwin(dtId, pazienteDT, BasicDigitalTwin.class);
        System.out.println(basicTwinResponse.getId());
    }

    public static void deletePaziente(String idPaziente) {
        Client.getClient().listRelationships(idPaziente, BasicRelationship.class)
                .forEach(rel -> Client.getClient().deleteRelationship(idPaziente, rel.getId()));
        Client.getClient().deleteDigitalTwin(idPaziente);
    }

    public static ArrayList<FiscalCode> getAllPatientId(){
        ArrayList<FiscalCode> pazientiIds = new ArrayList<>();
        String query = "SELECT $dtId FROM DIGITALTWINS T WHERE T.$metadata.$model = '"+ Constants.PAZIENTE_ID + "'";
        PagedIterable<BasicDigitalTwin> pageableResponse = Client.getClient().query(query, BasicDigitalTwin.class);
        pageableResponse.forEach(r-> pazientiIds.add(new FiscalCode(r.getId())));
        return pazientiIds;
    }
}
