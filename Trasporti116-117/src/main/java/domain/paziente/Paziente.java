/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.paziente;

public class Paziente {
    private StatoDiSalute statoDiSalute;
    private DatiAnagraficiPaziente datiAnagraficiPaziente;
    private Autonomia autonomia;

    public Paziente(StatoDiSalute statoDiSalute, DatiAnagraficiPaziente datiAnagraficiPaziente, Autonomia autonomia) {
        this.statoDiSalute = statoDiSalute;
        this.datiAnagraficiPaziente = datiAnagraficiPaziente;
        this.autonomia = autonomia;
    }


    public StatoDiSalute getStatoDiSalute() {
        return statoDiSalute;
    }

    public DatiAnagraficiPaziente getDatiAnagraficiPaziente() {
        return datiAnagraficiPaziente;
    }

    public Autonomia getAutonomia() {
        return autonomia;
    }
}
