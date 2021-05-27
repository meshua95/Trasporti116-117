/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.paziente;

import model.*;

public class Paziente {
    private FiscalCode fiscalCode;
    private StatoDiSalute statoDiSalute;
    private PersonalData datiAnagraficiPaziente;
    private Autonomy autonomy;

    public Paziente(FiscalCode cf, StatoDiSalute statoDiSalute, PersonalData datiAnagraficiPaziente, Autonomy autonomy) {
        this.fiscalCode = cf;
        this.statoDiSalute = statoDiSalute;
        this.datiAnagraficiPaziente = datiAnagraficiPaziente;
        this.autonomy = autonomy;
    }

    public StatoDiSalute getStatoDiSalute() {
        return statoDiSalute;
    }

    public PersonalData getDatiAnagraficiPaziente() {
        return datiAnagraficiPaziente;
    }

    public Autonomy getAutonomy() {
        return this.autonomy;
    }
}
