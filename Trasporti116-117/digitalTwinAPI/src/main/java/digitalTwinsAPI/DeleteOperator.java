/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package digitalTwinsAPI;

import domain.transport.operator.OperatorId;

/**
 * Contains delete operator digital twin API
 */
public class DeleteOperator {
    private DeleteOperator() {}
    /**
     * Delete a operator digital twin
     *
     * @param  idOperator id of the operator to be canceled
     */
    public static void deleteOperator(OperatorId idOperator) {
        Client.getClient().deleteDigitalTwin(idOperator.getOperatorId());
    }

}
