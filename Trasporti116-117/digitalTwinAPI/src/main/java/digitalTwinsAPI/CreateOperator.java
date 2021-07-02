/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */
package digitalTwinsAPI;

import com.azure.core.http.rest.Response;
import com.azure.core.util.Context;
import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicDigitalTwinMetadata;
import com.azure.digitaltwins.core.models.CreateOrReplaceDigitalTwinOptions;
import domain.transport.operator.OperatorId;
import domain.transport.operator.OperatorPersonalData;
import utils.Constants;

/**
 * Contains create operator digital twin API
 */
public class CreateOperator {
    private CreateOperator(){}
    /**
     * Create a operator digital twin
     *
     * @param operatorId id of operator
     * @param personalData personal data of operator
     *
     * @return id of the ambulance created
     */
    public static String createOperator(OperatorId operatorId, OperatorPersonalData personalData){
        BasicDigitalTwin operatorDT = new BasicDigitalTwin(operatorId.getOperatorId())
                .setMetadata(
                        new BasicDigitalTwinMetadata().setModelId(Constants.OPERATOR_MODEL_ID)
                )
                .addToContents("personalData", personalData);

        Response<BasicDigitalTwin> basicTwinResponse = Client.getClient().createOrReplaceDigitalTwinWithResponse(operatorId.getOperatorId(), operatorDT, BasicDigitalTwin.class, new CreateOrReplaceDigitalTwinOptions(),
                new Context("Key", "Value"));
        System.out.println(basicTwinResponse.getStatusCode());
        return basicTwinResponse.getValue().getId();
    }
}
