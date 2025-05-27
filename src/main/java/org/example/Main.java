package org.example;

import View.BiluthyrningGUI;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Main {
    public static void main(String[] args) {
        // 1️ Be användaren ange sitt personnummer
        String personnummer = JOptionPane.showInputDialog(null,
                "Ange ditt personnummer (YYYYMMDD-XXXX):",
                "Åldersverifiering", JOptionPane.QUESTION_MESSAGE);

        // 2️ Kontrollera om användaren tryckte på "Avbryt" eller lämnade rutan tom
        if (personnummer == null || personnummer.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "⚠️ Du måste ange ett personnummer!", "Fel", JOptionPane.ERROR_MESSAGE);
            System.exit(0); // Avsluta programmet
        }

        // 3️ Validera personnumrets längd (bör vara minst 10 siffror: YYYYMMDD-XXXX)
        if (personnummer.length() < 10) {
            JOptionPane.showMessageDialog(null, "❌ Ogiltigt personnummer! Ange minst 10 siffror.", "Fel", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        // 4️ Extrahera födelsedatum och validera det
        String födelsedatum = personnummer.substring(0, 8); // YYYYMMDD
        LocalDate födelsedag;

        try {
            födelsedag = LocalDate.parse(födelsedatum, DateTimeFormatter.ofPattern("yyyyMMdd"));
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "❌ Ogiltigt datum i personnumret! Kontrollera att dag och månad är korrekta.", "Fel", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
            return;
        }

        // 5⃣ Hämta dagens datum
        LocalDate idag = LocalDate.now();

        // 6️ Beräkna ålder
        long ålder = ChronoUnit.YEARS.between(födelsedag, idag);

        // 7⃣ Om personen är under 18, visa felmeddelande och avsluta
        if (ålder < 18) {
            JOptionPane.showMessageDialog(null,
                    "⛔ Du måste vara minst 18 år för att boka en bil!\nDin ålder: " + ålder + " år.",
                    "Åtkomst nekad", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        // 8️ Om personen är 18 eller äldre, öppna GUI
        SwingUtilities.invokeLater(BiluthyrningGUI::new);
    }
}
