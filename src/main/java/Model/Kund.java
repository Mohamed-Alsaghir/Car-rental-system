package Model;

public class Kund {
    private String namn;
    private String telefonnummer;

    // Konstruktor - Skapar en kund med namn och telefonnummer
    public Kund(String namn, String telefonnummer) {
        this.namn = namn;
        this.telefonnummer = telefonnummer;
    }

    // Getters - Hämtar kundens information
    public String getNamn() {
        return namn;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    // toString() - Returnerar kundens information i textformat
    @Override
    public String toString() {
        return "Kund: " + namn + " - Telefon: " + telefonnummer;
    }
}
