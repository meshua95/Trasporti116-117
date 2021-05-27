/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.paziente;

import model.*;

public class Patient {
    private FiscalCode fiscalCode;
    private HealthState healthState;
    private PersonalData personalData;
    private Autonomy autonomy;

    public Patient(FiscalCode cf, HealthState healthState, PersonalData personalData, Autonomy autonomy) {
        this.fiscalCode = cf;
        this.healthState = healthState;
        this.personalData = personalData;
        this.autonomy = autonomy;
    }

    public HealthState getStatoDiSalute() {
        return healthState;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public Autonomy getAutonomy() {
        return this.autonomy;
    }

    public FiscalCode getFiscalCode(){
        return this.fiscalCode;
    }
}
