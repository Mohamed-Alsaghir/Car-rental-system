package View;

import Controller.BiluthyrningsController;
import Model.Bil;
import Model.Filhantering;
import Model.Uthyrning;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BiluthyrningGUI extends JFrame {
    private BiluthyrningsController controller;
    private DefaultListModel<String> bilListModel;
    private DefaultListModel<String> uthyrningListModel;
    private JList<String> bilLista;
    private JList<String> uthyrningLista;

    public BiluthyrningGUI() {
        // Skapa listor för bilar och uthyrningar
        ArrayList<Bil> bilar = new ArrayList<>();
        ArrayList<Uthyrning> uthyrningar = new ArrayList<>();
        controller = new BiluthyrningsController(bilar, uthyrningar);

        // *** Fönsterinställningar ***
        setTitle("Biluthyrning System 🚗");
        setSize(750, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 240, 240));

        // *** Lista över tillgängliga bilar ***
        bilListModel = new DefaultListModel<>();
        bilLista = new JList<>(bilListModel);
        bilLista.setFont(new Font("Arial", Font.PLAIN, 14));
        bilLista.setFixedCellHeight(25);
        uppdateraBilLista();

        // *** Lista över uthyrda bilar ***
        uthyrningListModel = new DefaultListModel<>();
        uthyrningLista = new JList<>(uthyrningListModel);
        uthyrningLista.setFont(new Font("Arial", Font.PLAIN, 14));
        uthyrningLista.setFixedCellHeight(25);
        uppdateraUthyrningsLista();



        // *** Knapp: Lägg till bil ***
        JButton btnLaggTillBil = new JButton("🚗 Lägg till bil");
        btnLaggTillBil.setFont(new Font("Arial", Font.BOLD, 14));
        btnLaggTillBil.setBackground(new Color(60, 179, 113));
        btnLaggTillBil.setForeground(Color.WHITE);
        btnLaggTillBil.setFocusPainted(false);
        btnLaggTillBil.addActionListener(e -> laggTillBil());

        // *** Knapp: Hyr bil ***
        JButton btnHyrBil = new JButton("🔑 Hyr bil");
        btnHyrBil.setFont(new Font("Arial", Font.BOLD, 14));
        btnHyrBil.setBackground(new Color(70, 130, 180));
        btnHyrBil.setForeground(Color.WHITE);
        btnHyrBil.setFocusPainted(false);
        btnHyrBil.addActionListener(e -> hyrBil());

        // *** Knapp: Visa pris ***
        JButton btnVisaPris = new JButton("💰 Visa pris");
        btnVisaPris.setFont(new Font("Arial", Font.BOLD, 14));
        btnVisaPris.setBackground(new Color(255, 165, 0));
        btnVisaPris.setForeground(Color.WHITE);
        btnVisaPris.setFocusPainted(false);
        btnVisaPris.addActionListener(e -> visaPris());

        // *** Knapp: Lämna tillbaka bil ***
        JButton btnLamnaTillbakaBil = new JButton("🔄 Lämna tillbaka bil");
        btnLamnaTillbakaBil.setFont(new Font("Arial", Font.BOLD, 14));
        btnLamnaTillbakaBil.setBackground(new Color(255, 69, 0)); // Röd/orange färg
        btnLamnaTillbakaBil.setForeground(Color.WHITE);
        btnLamnaTillbakaBil.setFocusPainted(false);
        btnLamnaTillbakaBil.addActionListener(e -> lamnaTillbakaBil());


        JButton btnSparaBilar = new JButton("💾 Spara bilar");
        btnSparaBilar.setFont(new Font("Arial", Font.BOLD, 14));
        btnSparaBilar.setBackground(new Color(128, 128, 255)); // Blå färg
        btnSparaBilar.setForeground(Color.WHITE);
        btnSparaBilar.setFocusPainted(false);
        btnSparaBilar.addActionListener(e -> sparaBilarTillFil());

// *** Knapp: Ladda bilar ***
        JButton btnLaddaBilar = new JButton("📂 Ladda bilar");
        btnLaddaBilar.setFont(new Font("Arial", Font.BOLD, 14));
        btnLaddaBilar.setBackground(new Color(255, 165, 0)); // Orange färg
        btnLaddaBilar.setForeground(Color.WHITE);
        btnLaddaBilar.setFocusPainted(false);
        btnLaddaBilar.addActionListener(e -> laddaBilarFranFil());




        // *** Layout för knappar ***
        JPanel knappPanel = new JPanel();
        knappPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        knappPanel.add(btnLaggTillBil);
        knappPanel.add(btnHyrBil);
        knappPanel.add(btnVisaPris);
        knappPanel.add(btnLamnaTillbakaBil);
        knappPanel.add(btnSparaBilar);
        knappPanel.add(btnLaddaBilar);

        knappPanel.setBackground(new Color(240, 240, 240));

        // *** Panel för listor ***
        JPanel bilPanel = new JPanel(new BorderLayout());
        bilPanel.add(new JLabel("📋 Tillgängliga bilar:", JLabel.CENTER), BorderLayout.NORTH);
        bilPanel.add(new JScrollPane(bilLista), BorderLayout.CENTER);

        JPanel uthyrningPanel = new JPanel(new BorderLayout());
        uthyrningPanel.add(new JLabel("📜 Uthyrda bilar:", JLabel.CENTER), BorderLayout.NORTH);
        uthyrningPanel.add(new JScrollPane(uthyrningLista), BorderLayout.CENTER);

        // *** Lägg listorna i en GridLayout ***
        JPanel listPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        listPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        listPanel.add(bilPanel);
        listPanel.add(uthyrningPanel);

        // *** Lägg till komponenter i fönstret ***
        add(listPanel, BorderLayout.CENTER);
        add(knappPanel, BorderLayout.SOUTH);

        setVisible(true); // Visa fönstret
    }

    private void laddaBilarFranFil() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Välj fil att ladda bilar från");
        int userSelection = fileChooser.showOpenDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filvag = fileChooser.getSelectedFile().getAbsolutePath();
            ArrayList<Bil> bilar = Filhantering.lasaBilar(filvag);

            for (Bil bil : bilar) {
                controller.addCar(bil);
            }
            uppdateraBilLista();
            JOptionPane.showMessageDialog(this, "📂 Bilar har laddats från " + filvag);
        }
    }

    private void sparaBilarTillFil() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Välj var du vill spara filen");
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filvag = fileChooser.getSelectedFile().getAbsolutePath();
            Filhantering.sparaBilar(controller.getTillgangligaBilar(), filvag);
            JOptionPane.showMessageDialog(this, "✅ Bilar har sparats till " + filvag);
        }
    }


    // *** Metod: Lämna tillbaka bil ***
    private void lamnaTillbakaBil() {
        // 1️⃣ Hämta vald uthyrning från listan
        String valdUthyrning = uthyrningLista.getSelectedValue();

        // 2️⃣ Kontrollera om användaren har valt en uthyrning
        if (valdUthyrning == null) {
            JOptionPane.showMessageDialog(this, "⚠️ Välj en uthyrning att lämna tillbaka!");
            return; // Avslutar metoden om ingen uthyrning är vald
        }

        // 3️⃣ Leta efter den valda uthyrningen i listan över alla uthyrningar
        ArrayList<Uthyrning> uthyrningar = controller.getAllaUthyrningar();
        for (int i = 0; i < uthyrningar.size(); i++) { // Skriver for-loopen på ett traditionellt sätt
            Uthyrning uthyrning = uthyrningar.get(i); // Hämtar varje uthyrning i listan

            // 4️⃣ Skapa en textsträng för att jämföra med användarens val
            String uthyrningsText = uthyrning.getKundNamn() + " hyr " + uthyrning.getBil().getRegistreringsnummer();

            // 5️⃣ Kontrollera om den valda uthyrningen matchar någon uthyrning i listan
            if (valdUthyrning.contains(uthyrningsText)) {
                Bil bilAttLamnaTillbaka = uthyrning.getBil(); // Hämtar bilen som ska lämnas tillbaka

                // 6️⃣ Ta bort uthyrningen från listan
                controller.taBortUthyrning(uthyrning);

                // 7️⃣ Lägg tillbaka bilen i listan över tillgängliga bilar
                controller.addCar(bilAttLamnaTillbaka);

                // 8️⃣ Uppdatera listorna i GUI så att användaren ser förändringen
                uppdateraBilLista();
                uppdateraUthyrningsLista();

                // 9️⃣ Visa en bekräftelse att bilen har lämnats tillbaka
                JOptionPane.showMessageDialog(this, "✅ Bilen " + bilAttLamnaTillbaka.getRegistreringsnummer() + " har lämnats tillbaka!");

                return; // Avslutar metoden när vi hittat och bearbetat bilen
            }
        }
    }


    // *** Metod: Uppdatera listan över tillgängliga bilar ***
    private void uppdateraBilLista() {
        bilListModel.clear();
        for (Bil bil : controller.getTillgangligaBilar()) {
            bilListModel.addElement(bil.getRegistreringsnummer() + " - " + bil.getMarke() + " " + bil.getModell());
        }
    }

    // *** Metod: Hyr bil och visa totalpris ***
    private void hyrBil() {
        String valdBilInfo = bilLista.getSelectedValue();
        if (valdBilInfo == null) {
            JOptionPane.showMessageDialog(this, "⚠️ Välj en bil att hyra!");
            return;
        }

        String input = JOptionPane.showInputDialog("Ange kundens namn och antal dagar:\nExempel: Ali,5");

        if (input != null && !input.isEmpty()) {
            String[] delar = input.split(",");
            if (delar.length == 2) {
                String kundNamn = delar[0].trim();
                int dagar;

                try {
                    dagar = Integer.parseInt(delar[1].trim());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "❌ Felaktigt antal dagar, försök igen!");
                    return;
                }

                String regNr = valdBilInfo.split(" - ")[0];
                Bil bil = controller.getBilByRegNr(regNr);

                if (bil != null) {
                    double totalPris = bil.calculatePrice(dagar); // Beräknar totalpriset

                    // Lägg till uthyrningen
                    controller.hyrBil(bil, kundNamn, dagar);
                    uppdateraBilLista();
                    uppdateraUthyrningsLista();

                    // Visa totalpriset i en popup-ruta
                    JOptionPane.showMessageDialog(this, "✅ " + kundNamn + " har hyrt " +
                            bil.getMarke() + " " + bil.getModell() + " i " + dagar + " dagar.\n" +
                            "💰 Totalt pris: " + totalPris + " kr");

                } else {
                    JOptionPane.showMessageDialog(this, "❌ Bilen hittades inte!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "⚠️ Felaktigt format, försök igen!");
            }
        }
    }


    // *** Metod: Uppdatera listan över uthyrda bilar ***
    private void uppdateraUthyrningsLista() {
        uthyrningListModel.clear();
        for (Uthyrning uthyrning : controller.getAllaUthyrningar()) {
            double totalPris = uthyrning.beraknaTotalPris();
            uthyrningListModel.addElement(uthyrning.getKundNamn() + " hyr " +
                    uthyrning.getBil().getRegistreringsnummer() + " - 💰 " + totalPris + " kr");
        }
    }

    // *** Metod: Lägg till en bil ***
    private void laggTillBil() {
        String input = JOptionPane.showInputDialog("Ange bilens uppgifter (RegNr, Märke, Modell, PrisPerDag):\nExempel: ABC123,Toyota,Corolla,400");

        if (input != null && !input.isEmpty()) {
            String[] delar = input.split(",");
            if (delar.length == 4) {
                String regNr = delar[0].trim();
                String marke = delar[1].trim();
                String modell = delar[2].trim();
                double pris = Double.parseDouble(delar[3].trim());

                controller.addCar(new Bil(regNr, marke, modell, pris));
                uppdateraBilLista();
            } else {
                JOptionPane.showMessageDialog(this, "❌ Felaktigt format, försök igen!");
            }
        }
    }

    // *** Metod: Visa pris för vald uthyrning ***
    private void visaPris() {
        // 1️⃣ Hämta vald bil från "Tillgängliga bilar"
        String valdBil = bilLista.getSelectedValue();
        String valdUthyrning = uthyrningLista.getSelectedValue();

        // 2️⃣ Om användaren har valt en bil från "Tillgängliga bilar", visa bilens pris per dag
        if (valdBil != null) {
            String regNr = valdBil.split(" - ")[0]; // Hämta registreringsnumret
            Bil bil = controller.getBilByRegNr(regNr); // Hitta bilen i systemet

            if (bil == null) {
                // ❌ Om bilen inte hittas, visa ett felmeddelande
                JOptionPane.showMessageDialog(this, "❌ Bilen med registreringsnummer " + regNr + " hittades inte i systemet!");
                return;
            }

            // ✅ Om bilen hittas, visa bilens pris
            JOptionPane.showMessageDialog(this,
                    "🚗 Bilinformation:\n" +
                            "Märke: " + bil.getMarke() + "\n" +
                            "Modell: " + bil.getModell() + "\n" +
                            "💰 Pris per dag: " + bil.getPrisPerDag() + " kr",
                    "Bilens pris", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        for (Uthyrning uthyrning : controller.getAllaUthyrningar()) {
            String uthyrningsText = uthyrning.getKundNamn() + " hyr " + uthyrning.getBil().getRegistreringsnummer();
            if (valdUthyrning.contains(uthyrningsText)) {
                double totalPris = uthyrning.beraknaTotalPris();

                JOptionPane.showMessageDialog(this,
                        "📜 Uthyrning:\nKund: " + uthyrning.getKundNamn() +
                                "\nBil: " + uthyrning.getBil().getMarke() + " " + uthyrning.getBil().getModell() +
                                "\nAntal dagar: " + uthyrning.getAntalDagar() +
                                "\n💰 Totalt pris: " + totalPris + " kr",
                        "Pris för uthyrning", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
    }

}