/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain;

/**
 * Class that represents postal code number
 */
public class DTDLPostalCode {
    private final int postalCode;

    /**
     * Create a new postal code number
     *
     * @param pC postal code number
     */
    public DTDLPostalCode(final int pC) {
        this.postalCode = pC;
    }

    /**
     * @return postal code number
     */
    public int getPostalCode() {
        return this.postalCode;
    }
}
