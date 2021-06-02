/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.operatore;

import com.azure.core.http.rest.PagedIterable;
import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicDigitalTwinMetadata;
import com.azure.digitaltwins.core.BasicRelationship;
import digitalTwins.Client;
import model.OperatorId;
import model.PersonalData;
import utils.Constants;

import java.util.ArrayList;

public class OperatorDigitalTwin {

    public static void createOperatore(OperatorId operatorId, PersonalData personalData){
        BasicDigitalTwin OperatoreAmbulanzaDT = new BasicDigitalTwin(operatorId.getOperatorId())
                .setMetadata(
                        new BasicDigitalTwinMetadata().setModelId(Constants.OPERATOR_MODEL_ID)
                )
                .addToContents("personalData", personalData);

        BasicDigitalTwin basicTwinResponse = Client.getClient().createOrReplaceDigitalTwin(operatorId.getOperatorId(), OperatoreAmbulanzaDT, BasicDigitalTwin.class);
        System.out.println(basicTwinResponse.getId());
    }

    public static void deleteOperatore(OperatorId idOperatore) {
        Client.getClient().listRelationships(idOperatore.getOperatorId(), BasicRelationship.class)
                .forEach(rel -> Client.getClient().deleteRelationship(idOperatore.getOperatorId(), rel.getId()));
        Client.getClient().deleteDigitalTwin(idOperatore.getOperatorId());
    }

    public static ArrayList<OperatorId> getAllOperatoreId(){
        ArrayList<OperatorId> operatoriIds = new ArrayList<>();
        String query = "SELECT $dtId FROM DIGITALTWINS WHERE WHERE IS_OF_MODEL('"+ Constants.OPERATOR_MODEL_ID + "')";
        PagedIterable<BasicDigitalTwin> pageableResponse = Client.getClient().query(query, BasicDigitalTwin.class);
        pageableResponse.forEach(r-> operatoriIds.add(new OperatorId(r.getId())));
        return operatoriIds;
    }
}
