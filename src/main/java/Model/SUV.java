package Model;

public class SUV extends Bil{

    private boolean fyrhjulsdrift;

    // Konstruktor
    public SUV(String registreringsnummer, String marke, String modell, double prisPerDag, boolean fyrhjulsdrift) {
        super(registreringsnummer, marke, modell, prisPerDag);
        this.fyrhjulsdrift = fyrhjulsdrift;
    }

    // Getter
    public boolean harFyrhjulsdrift() {
        return fyrhjulsdrift;
    }

    // toString() för att visa SUV-info
    @Override
    public String toString() {
        return super.toString() + " - Fyrhjulsdrift: " + (fyrhjulsdrift ? "Ja" : "Nej");
    }
}

