/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.trasporto;

public enum StatoTrasporto {
    NON_INIZIATO(1),
    IN_CORSO(2),
    CONCLUSO(3),
    ANNULLATO(0);

    private int value;

    StatoTrasporto(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return this.value;
    }
}
