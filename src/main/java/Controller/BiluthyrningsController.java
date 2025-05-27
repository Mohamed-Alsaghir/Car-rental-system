package Controller;

import Model.Bil;
import Model.Uthyrning;
import java.util.ArrayList;

public class BiluthyrningsController {
    private final ArrayList<Bil> bilar;
    private final ArrayList<Uthyrning> uthyrningar;

    public BiluthyrningsController(ArrayList<Bil> bilar, ArrayList<Uthyrning> uthyrningar) {
        this.bilar = bilar;
        this.uthyrningar = uthyrningar;
    }

    public void addCar(Bil bil) {
        bilar.add(bil);
    }

    public void hyrBil(Bil bil, String kundNamn, int dagar) {
        if (bilar.contains(bil)) {
            uthyrningar.add(new Uthyrning(bil, kundNamn, dagar));
            bilar.remove(bil);
        } else {
            System.out.println("Bilen är inte tillgänglig för uthyrning.");
        }
    }

    public Bil getBilByRegNr(String regNr) {
        for (Bil bil : bilar) {
            if (bil.getRegistreringsnummer().equals(regNr)) {
                return bil;
            }
        }
        return null; // Returnerar null om ingen match hittas
    }

    public ArrayList<Bil> getTillgangligaBilar() {
        return new ArrayList<>(bilar); // Returnerar en kopia av listan över tillgängliga bilar
    }

    public ArrayList<Uthyrning> getAllaUthyrningar() {
        return new ArrayList<>(uthyrningar); // Returnerar en kopia av listan över alla uthyrningar
    }

    // *** Metod: Ta bort en uthyrning ***
    public void taBortUthyrning(Uthyrning uthyrning) {
        uthyrningar.remove(uthyrning);
    }


    public void visaTillgangligaBilar() {
        System.out.println("Tillgängliga bilar:");
        for (Bil bil : bilar) {
            System.out.println(bil);
        }
    }

    public void visaUthyrningar() {
        System.out.println("Aktuella uthyrningar:");
        for (Uthyrning uthyrning : uthyrningar) {
            System.out.println(uthyrning);
        }
    }
}
