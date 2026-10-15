import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Main extends JFrame {
    JTextField imie = new JTextField("Damian");
    JTextField nazwisko = new JTextField("Szleja");
    JComboBox<String> stanowisko = new JComboBox<>(new String[] {
        "Kierownik", "Starszy programista", "Młodszy programista", "Tester"
    });
    JTextField ileZnakow = new JTextField();
    JCheckBox litery = new JCheckBox("Małe i wielkie litery", true);
    JCheckBox cyfry = new JCheckBox("Cyfry");
    JCheckBox specjalne = new JCheckBox("Znaki specjalne");
    String haslo = "";
    Color tlo = new Color(176, 196, 222);

    public Main() {
        setTitle("Dodaj pracownika. Damian Szleja");
        setSize(730, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel(null);
        panel.setBackground(tlo);
        setContentPane(panel);
        JPanel dane = new JPanel(null);
        dane.setBackground(tlo);
        dane.setBorder(BorderFactory.createTitledBorder("Dane Pracownika"));
        dane.setBounds(25, 25, 300, 220);
        panel.add(dane);
        dodajPole(dane, "Imię", imie, 35);
        dodajPole(dane, "Nazwisko", nazwisko, 80);
        dodajPole(dane, "Stanowisko", stanowisko, 125);
        stanowisko.setSelectedIndex(-1);
        JPanel hasla = new JPanel(null);
        hasla.setBackground(tlo);
        hasla.setBorder(BorderFactory.createTitledBorder("Generowanie hasła"));
        hasla.setBounds(370, 25, 310, 220);
        panel.add(hasla);
        dodajPole(hasla, "Ile znaków?", ileZnakow, 30);
        JCheckBox[] pola = {litery, cyfry, specjalne};
        for (int i = 0; i < pola.length; i++) {
            pola[i].setBounds(20, 65 + i * 30, 260, 25);
            pola[i].setBackground(tlo);
            hasla.add(pola[i]);
        }
        JButton generuj = przycisk("Generuj hasło");
        generuj.setBounds(95, 170, 130, 30);
        hasla.add(generuj);
        generuj.addActionListener(e -> generujHaslo());
        JButton zatwierdz = przycisk("Zatwierdź");
        zatwierdz.setBounds(230, 260, 260, 30);
        panel.add(zatwierdz);
        zatwierdz.addActionListener(e -> JOptionPane.showMessageDialog(this,
            "Dane pracownika: " + imie.getText() + " " + nazwisko.getText() + " "
            + (stanowisko.getSelectedItem() == null ? "" : stanowisko.getSelectedItem())
            + " Hasło: " + haslo));
    }

    void dodajPole(JPanel panel, String tekst, JComponent pole, int y) {
        JLabel etykieta = new JLabel(tekst);
        etykieta.setBounds(20, y, 100, 28);
        pole.setBounds(125, y, 160, 28);
        panel.add(etykieta);
        panel.add(pole);
    }

    JButton przycisk(String tekst) {
        JButton przycisk = new JButton(tekst);
        przycisk.setBackground(new Color(70, 130, 180));
        przycisk.setForeground(Color.WHITE);
        return przycisk;
    }

    void generujHaslo() {
        int dlugosc;
        int wymagane = 0;
        if (litery.isSelected()) wymagane++;
        if (cyfry.isSelected()) wymagane++;
        if (specjalne.isSelected()) wymagane++;
        try {
            dlugosc = Integer.parseInt(ileZnakow.getText());
        } catch (NumberFormatException blad) {
            JOptionPane.showMessageDialog(this, "Podaj poprawną liczbę znaków.");
            return;
        }
        if (dlugosc < Math.max(1, wymagane)) {
            JOptionPane.showMessageDialog(this, "Za mało znaków dla wybranych opcji.");
            return;
        }
        haslo = losujHaslo(dlugosc, litery.isSelected(), cyfry.isSelected(), specjalne.isSelected());
        JOptionPane.showMessageDialog(this, haslo);
    }

    static String losujHaslo(int dlugosc, boolean wielkie, boolean cyfry, boolean specjalne) {
        Random los = new Random();
        String maleLitery = "abcdefghijklmnopqrstuvwxyz";
        String wielkieLitery = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String liczby = "0123456789";
        String znaki = "!@#$%^&*()_+-=";
        String wynik = "";
        if (wielkie) wynik += wielkieLitery.charAt(los.nextInt(wielkieLitery.length()));
        if (cyfry) wynik += liczby.charAt(los.nextInt(liczby.length()));
        if (specjalne) wynik += znaki.charAt(los.nextInt(znaki.length()));
        while (wynik.length() < dlugosc) {
            wynik += maleLitery.charAt(los.nextInt(maleLitery.length()));
        }
        return wynik;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
