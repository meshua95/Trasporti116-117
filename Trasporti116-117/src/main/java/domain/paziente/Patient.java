/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.paziente;

import model.*;

public class Patient {
    private FiscalCode fiscalCode;
    private HealthState healthState;
    private PersonalData datiAnagraficiPaziente;
    private Autonomy autonomy;

    public Patient(FiscalCode cf, HealthState healthState, PersonalData datiAnagraficiPaziente, Autonomy autonomy) {
        this.fiscalCode = cf;
        this.healthState = healthState;
        this.datiAnagraficiPaziente = datiAnagraficiPaziente;
        this.autonomy = autonomy;
    }

    public HealthState getStatoDiSalute() {
        return healthState;
    }

    public PersonalData getDatiAnagraficiPaziente() {
        return datiAnagraficiPaziente;
    }

    public Autonomy getAutonomy() {
        return this.autonomy;
    }

    public FiscalCode getFiscalCode(){
        return this.fiscalCode;
    }
}
