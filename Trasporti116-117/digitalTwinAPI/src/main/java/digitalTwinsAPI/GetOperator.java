/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */
package digitalTwinsAPI;

import com.azure.core.http.rest.PagedIterable;
import com.azure.digitaltwins.core.BasicDigitalTwin;
import domain.transport.operator.OperatorId;
import utils.Constants;
import utils.WaitForClientResponse;
import utils.errorCode.QueryTimeOutException;

import java.util.ArrayList;

/**
 * Get operator digital twin API
 */
public class GetOperator {
    private GetOperator(){}

    /**
     * Get all digital twin operator
     *
     * @return id of all operator
     * @throws QueryTimeOutException if the server takes too long to respond
     */
    public static ArrayList<OperatorId> getAllOperatorId() throws QueryTimeOutException {
        ArrayList<OperatorId> operatoriIds = new ArrayList<>();
        String query = "SELECT $dtId FROM DIGITALTWINS WHERE IS_OF_MODEL('"+ Constants.OPERATOR_MODEL_ID + "')";
        PagedIterable<BasicDigitalTwin> pageableResponse = Client.getClient().query(query, BasicDigitalTwin.class);

        WaitForClientResponse.waitForClientResponseIfExist(pageableResponse);

        pageableResponse.forEach(r-> operatoriIds.add(new OperatorId(r.getId())));

        return operatoriIds;
    }
}
