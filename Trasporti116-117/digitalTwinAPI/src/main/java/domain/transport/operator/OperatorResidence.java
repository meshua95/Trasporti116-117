/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.transport.operator;
import domain.DTDLAddress;
import domain.DTDLCity;
import domain.DTDLHouseNumber;
import domain.DTDLDistrict;
import domain.DTDLPostalCode;

/**
 * Class that represents the operator residence
 */
public class OperatorResidence {

    private final DTDLAddress address;
    private final DTDLHouseNumber houseNumber;
    private final DTDLCity city;
    private final DTDLDistrict district;
    private final DTDLPostalCode postalCode;

    /**
     * Create operator residence
     * @param a operator residence address
     * @param hn operator residence house number
     * @param c operator residence city
     * @param d operator residence district
     * @param pc operator residence postal code
     */
    public OperatorResidence(final DTDLAddress a, final DTDLHouseNumber hn, final DTDLCity c, final DTDLDistrict d, final DTDLPostalCode pc) {
        this.address = a;
        this.houseNumber = hn;
        this.city = c;
        this.district = d;
        this.postalCode = pc;
    }

    /**
     * @return address
     */
    public DTDLAddress getAddress() {
        return address;
    }

    /**
     * @return house number
     */
    public DTDLHouseNumber getHouseNumber() {
        return houseNumber;
    }

    /**
     * @return city
     */
    public DTDLCity getCity() {
        return city;
    }

    /**
     * @return district
     */
    public DTDLDistrict getDistrict() {
        return district;
    }

    /**
     * @return postal code
     */
    public DTDLPostalCode getPostalCode() {
        return postalCode;
    }
}
