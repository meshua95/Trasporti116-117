/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package digitalTwinsAPI;

import com.azure.digitaltwins.core.BasicRelationship;
import domain.transport.operator.OperatorId;

public class DeleteOperator {
    /**
     * Delete a operator digital twin
     *
     * @param  idOperator id of the operator to be canceled
     */
    public static void deleteOperator(OperatorId idOperator) {
        Client.getClient().listRelationships(idOperator.getOperatorId(), BasicRelationship.class)
                .forEach(rel -> Client.getClient().deleteRelationship(idOperator.getOperatorId(), rel.getId()));
        Client.getClient().deleteDigitalTwin(idOperator.getOperatorId());
    }

}
