package Model;

public class Sportbil extends Bil{

    private int hästkrafter;

    public Sportbil(String registreringsnummer, String marke, String modell, double prisPerDag, int hästkrafter) {
        super(registreringsnummer, marke, modell, prisPerDag);
        this.hästkrafter = hästkrafter;
    }

    public int getHästkrafter() {
        return hästkrafter;
    }

    @Override
    public String toString() {
        return "Sportbil{" + "hästkrafter=" + hästkrafter + '}';
    }
}
