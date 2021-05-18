/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */

package domain.paziente;

import java.time.LocalDate;

public class DatiAnagraficiPaziente {
    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private Residenza residenza;

    public DatiAnagraficiPaziente(String nome, String conome, LocalDate dataNascita, Residenza residenza) {
        this.nome = nome;
        this.cognome = conome;
        this.dataNascita = dataNascita;
        this.residenza = residenza;
    }

    public static class Residenza {
        private String via;
        private String numero;
        private String citta;
        private String provincia;
        private int CAP;

        public Residenza(String via, String numero, String citta, String provincia, int cap) {
            this.via = via;
            this.numero = numero;
            this.citta = citta;
            this.provincia = provincia;
            this.CAP = cap;
        }
    }
}
