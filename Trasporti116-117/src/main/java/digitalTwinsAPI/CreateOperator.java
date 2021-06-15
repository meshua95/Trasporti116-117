/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */
package digitalTwinsAPI;

import com.azure.digitaltwins.core.BasicDigitalTwin;
import com.azure.digitaltwins.core.BasicDigitalTwinMetadata;
import domain.transport.operator.OperatorId;
import domain.transport.operator.OperatorPersonalData;
import utils.Constants;

public class CreateOperator {
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

        BasicDigitalTwin basicTwinResponse = Client.getClient().createOrReplaceDigitalTwin(operatorId.getOperatorId(), operatorDT, BasicDigitalTwin.class);
        System.out.println(basicTwinResponse.getId());
        return basicTwinResponse.getId();
    }
}
