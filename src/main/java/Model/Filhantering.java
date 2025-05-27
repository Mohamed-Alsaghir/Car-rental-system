package Model;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Filhantering {
    // Metod för att välja en filväg med en filväljare
    private static String valjFilvag(boolean spara) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int resultat;
        if (spara) {
            resultat = fileChooser.showSaveDialog(null);
        } else {
            resultat = fileChooser.showOpenDialog(null);
        }

        if (resultat == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getAbsolutePath();
        }
        return null;
    }

    // Metod för att spara bilar
    public static void sparaBilar(ArrayList<Bil> bilar) {
        String filvag = valjFilvag(true);
        if (filvag == null) return; // Användaren avbröt

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filvag))) {
            for (Bil bil : bilar) {
                writer.write(bil.getRegistreringsnummer() + "," + bil.getMarke() + "," + bil.getModell() + "," + bil.getPrisPerDag());
                writer.newLine();
            }
            JOptionPane.showMessageDialog(null, "✅ Bilar sparade till: " + filvag);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "❌ Fel vid sparande av bilar: " + e.getMessage());
        }
    }

    // Metod för att läsa in bilar
    // Uppdatera metoderna i Filhantering för att ta emot en filväg
    public static ArrayList<Bil> lasaBilar(String filvag) {
        ArrayList<Bil> bilar = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filvag))) {
            String rad;
            while ((rad = reader.readLine()) != null) {
                String[] delar = rad.split(",");
                if (delar.length == 4) {
                    String regNr = delar[0];
                    String marke = delar[1];
                    String modell = delar[2];
                    double pris = Double.parseDouble(delar[3]);
                    bilar.add(new Bil(regNr, marke, modell, pris));
                }
            }
            System.out.println("📂 Bilar har lästs från: " + filvag);
        } catch (IOException e) {
            System.out.println("❌ Fel vid läsning av bilar: " + e.getMessage());
        }
        return bilar;
    }

    public static void sparaBilar(ArrayList<Bil> bilar, String filvag) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filvag))) {
            for (Bil bil : bilar) {
                writer.write(bil.getRegistreringsnummer() + "," + bil.getMarke() + "," + bil.getModell() + "," + bil.getPrisPerDag());
                writer.newLine();
            }
            System.out.println("✅ Bilar sparade till: " + filvag);
        } catch (IOException e) {
            System.out.println("❌ Fel vid sparande av bilar: " + e.getMessage());
        }
    }


    // Metod för att spara uthyrningar
    public static void sparaUthyrningar(ArrayList<Uthyrning> uthyrningar) {
        String filvag = valjFilvag(true);
        if (filvag == null) return;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filvag))) {
            for (Uthyrning uthyrning : uthyrningar) {
                writer.write(uthyrning.getKundNamn() + "," + uthyrning.getBil().getRegistreringsnummer() + "," + uthyrning.getAntalDagar());
                writer.newLine();
            }
            JOptionPane.showMessageDialog(null, "✅ Uthyrningar sparade till: " + filvag);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "❌ Fel vid sparande av uthyrningar: " + e.getMessage());
        }
    }

    // Metod för att läsa in uthyrningar
    public static ArrayList<Uthyrning> lasaUthyrningar(ArrayList<Bil> bilar) {
        String filvag = valjFilvag(false);
        if (filvag == null) return new ArrayList<>();

        ArrayList<Uthyrning> uthyrningar = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filvag))) {
            String rad;
            while ((rad = reader.readLine()) != null) {
                String[] delar = rad.split(",");
                if (delar.length == 3) {
                    String kundnamn = delar[0];
                    String regNr = delar[1];
                    int antalDagar = Integer.parseInt(delar[2]);

                    Bil hyrdBil = null;
                    for (Bil bil : bilar) {
                        if (bil.getRegistreringsnummer().equals(regNr)) {
                            hyrdBil = bil;
                            break;
                        }
                    }

                    if (hyrdBil != null) {
                        uthyrningar.add(new Uthyrning(hyrdBil, kundnamn, antalDagar));
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "📂 Uthyrningar lästa från: " + filvag);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "❌ Fel vid läsning av uthyrningar: " + e.getMessage());
        }
        return uthyrningar;
    }
}
