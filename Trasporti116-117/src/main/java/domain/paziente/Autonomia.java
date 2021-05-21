package domain.paziente;

public enum Autonomia {
    AUTONOMO(1),
    PARZIALMENTE_AUTONOMO(2),
    NON_AUTONOMO(3);

    private int value;

    Autonomia(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return this.value;
    }
}
