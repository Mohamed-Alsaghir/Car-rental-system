package Model;

public class Bil {
    private String registreringsnummer;
    private String marke;
    private String modell;
    private double prisPerDag;

    public Bil(String registreringsnummer, String marke, String modell, double prisPerDag) {
        this.registreringsnummer = registreringsnummer;
        this.marke = marke;
        this.modell = modell;
        this.prisPerDag = prisPerDag;
    }

    public String getRegistreringsnummer() {
        return registreringsnummer;
    }

    public String getMarke() {
        return marke;
    }

    public String getModell() {
        return modell;
    }

    public double getPrisPerDag() {
        return prisPerDag;
    }

    // Metod för att räkna ut pris för en viss uthyrningsperiod
    public double calculatePrice(int dagar){
        return prisPerDag * dagar;
    }

    @Override
    public String toString() {
        return "Bil{" + "registreringsnummer='" + registreringsnummer + '\'' +  ", marke='" + marke + '\'' +
                ", modell='" + modell + '\'' +  ", prisPerDag=" + prisPerDag + '}';
    }
}
