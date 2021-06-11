/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package digitalTwins.operator;

import com.azure.core.http.rest.PagedIterable;
import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicDigitalTwinMetadata;
import com.azure.digitaltwins.core.BasicRelationship;
import digitalTwins.Client;
import domain.transportBoundedContext.*;
import utils.Constants;

import java.util.ArrayList;

public class OperatorDigitalTwin {

    public static String createOperator(OperatorId operatorId, OperatorPersonalData personalData){
        BasicDigitalTwin operatorDT = new BasicDigitalTwin(operatorId.getOperatorId())
                .setMetadata(
                        new BasicDigitalTwinMetadata().setModelId(Constants.OPERATOR_MODEL_ID)
                )
                .addToContents("personalData", personalData);

        BasicDigitalTwin basicTwinResponse = Client.getClient().createOrReplaceDigitalTwin(operatorId.getOperatorId(), operatorDT, BasicDigitalTwin.class);
        System.out.println(basicTwinResponse.getId());
        return basicTwinResponse.getId();
    }

    public static void deleteOperatore(OperatorId idOperator) {
        Client.getClient().listRelationships(idOperator.getOperatorId(), BasicRelationship.class)
                .forEach(rel -> Client.getClient().deleteRelationship(idOperator.getOperatorId(), rel.getId()));
        Client.getClient().deleteDigitalTwin(idOperator.getOperatorId());
    }


    public static ArrayList<OperatorId> getAllOperatorId(){
        ArrayList<OperatorId> operatoriIds = new ArrayList<>();
        String query = "SELECT $dtId FROM DIGITALTWINS WHERE IS_OF_MODEL('"+ Constants.OPERATOR_MODEL_ID + "')";
        PagedIterable<BasicDigitalTwin> pageableResponse = Client.getClient().query(query, BasicDigitalTwin.class);
        pageableResponse.forEach(r-> operatoriIds.add(new OperatorId(r.getId())));
        return operatoriIds;
    }
}
