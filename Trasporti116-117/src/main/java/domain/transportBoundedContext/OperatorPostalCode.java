/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.transportBoundedContext;

public class OperatorPostalCode {
    private int postalCode;

    public OperatorPostalCode(int postalCode){
        this.postalCode = postalCode;
    }

    public int getPostalCode(){
        return this.postalCode;
    }
}
