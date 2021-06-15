/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */
package digitalTwinsAPI;

import com.azure.core.http.rest.PagedIterable;
import com.azure.digitaltwins.core.BasicDigitalTwin;
import domain.transport.operator.OperatorId;
import utils.Constants;

import java.util.ArrayList;

public class GetOperator {
    /**
     * Get all digital twin operator
     *
     * @return id of all operator
     */
    public static ArrayList<OperatorId> getAllOperatorId(){
        ArrayList<OperatorId> operatoriIds = new ArrayList<>();
        String query = "SELECT $dtId FROM DIGITALTWINS WHERE IS_OF_MODEL('"+ Constants.OPERATOR_MODEL_ID + "')";
        PagedIterable<BasicDigitalTwin> pageableResponse = Client.getClient().query(query, BasicDigitalTwin.class);
        pageableResponse.forEach(r-> operatoriIds.add(new OperatorId(r.getId())));
        return operatoriIds;
    }
}