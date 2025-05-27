package Model;

public class Uthyrning {
    private Bil bil;
    private String kundNamn;
    private int antalDagar;

    // Ändrad konstruktor - Tar nu en String istället för en Kund
    public Uthyrning(Bil bil, String kundNamn, int antalDagar) {
        this.bil = bil;
        this.kundNamn = kundNamn;
        this.antalDagar = antalDagar;
    }

    // Getter-metoder
    public Bil getBil() {
        return bil;
    }

    public String getKundNamn() {
        return kundNamn;
    }

    public int getAntalDagar() {
        return antalDagar;
    }

    // Metod för att beräkna totalpriset
    public double beraknaTotalPris() {
        return bil.calculatePrice(antalDagar);
    }

    @Override
    public String toString() {
        return "Uthyrning{" + "bil=" + bil + ", kundNamn='" + kundNamn + '\'' + ", antalDagar=" + antalDagar + '}';
    }
}