package domain.trasporto;

public class Itinerario {

    private Luogo partenza;
    private Luogo destinazione;

    public Itinerario(Luogo partenza, Luogo destinazione) {
        this.partenza = partenza;
        this.destinazione = destinazione;
    }


    public static class Luogo {
        private String via;
        private String numero;
        private String citta;
        private String provincia;
        private int CAP;

        public Luogo(String via, String numero, String citta, String provincia, int cap) {
            this.via = via;
            this.numero = numero;
            this.citta = citta;
            this.provincia = provincia;
            this.CAP = cap;
        }
    }
}
