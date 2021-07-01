/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.transport.operator;

/**
 * Class that represents the operator identifier
 */
public class OperatorId {
    private final String operatorId;

    /**
     * Create an operator identifier
     *
     * @param operatorId operator identifier
     */
    public OperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    /**
     * @return operator identifier
     */
    public String getOperatorId() {
        return this.operatorId;
    }
}
