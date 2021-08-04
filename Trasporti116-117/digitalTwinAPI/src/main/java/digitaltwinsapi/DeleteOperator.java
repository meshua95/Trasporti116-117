/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package digitaltwinsapi;

import domain.transport.operator.OperatorId;

/**
 * Contains delete operator digital twin API
 */
public final class DeleteOperator {
    private DeleteOperator() { }
    /**
     * Delete a operator digital twin
     *
     * @param  idOperator id of the operator to be canceled
     */
    public static void deleteOperator(final OperatorId idOperator) {
        Client.getClient().deleteDigitalTwin(idOperator.getId());
    }

}
