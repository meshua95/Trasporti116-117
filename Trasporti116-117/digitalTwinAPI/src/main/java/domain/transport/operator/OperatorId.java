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
     * @param id operator identifier
     */
    public OperatorId(final String id) {
        this.operatorId = id;
    }

    /**
     * @return operator identifier
     */
    public String getOperatorId() {
        return this.operatorId;
    }
}
