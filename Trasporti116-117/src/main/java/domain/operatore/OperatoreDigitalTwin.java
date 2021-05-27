/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.operatore;

import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicDigitalTwinMetadata;
import com.azure.digitaltwins.core.BasicRelationship;
import digitalTwins.Client;
import model.PersonalData;
import utils.Constants;

public class OperatoreDigitalTwin {

    public static void createOperatore(String dtId, PersonalData personalData){
        BasicDigitalTwin OperatoreAmbulanzaDT = new BasicDigitalTwin(dtId)
                .setMetadata(
                        new BasicDigitalTwinMetadata().setModelId(Constants.OPERATORE_AMBULANZA_ID)
                )
                .addToContents("datiAnagrafici", personalData);

        BasicDigitalTwin basicTwinResponse = Client.getClient().createOrReplaceDigitalTwin(dtId, OperatoreAmbulanzaDT, BasicDigitalTwin.class);
        System.out.println(basicTwinResponse.getId());
    }

    public static void deleteOperatore(String idOperatore) {
        Client.getClient().listRelationships(idOperatore, BasicRelationship.class)
                .forEach(rel -> Client.getClient().deleteRelationship(idOperatore, rel.getId()));
        Client.getClient().deleteDigitalTwin(idOperatore);
    }

}
