package com.pao.laboratory06.exercise1;

import java.util.Locale;
import java.util.Scanner;

public class Angajat implements Comparable<Angajat> {
    private String nume;
    private double salariu;

    public Angajat(String nume, double salariu) {
        this.nume = nume;
        this.salariu = salariu;
    }

    public static Angajat citeste(Scanner s) {
        String nume = s.next();
        double salariu = s.nextDouble();
        return new Angajat(nume, salariu);
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "%s %.1f", nume, salariu);  // nu imi mergea ca am regiunea setata pe RO
        /* partA       0/2 teste
    @@ -1,3 +1,3 @@
      - Zara 1200.0
      - Ana 1200.0
      - Maria 800.0
      + Zara 1200,0
      + Ana 1200,0
      + Maria 800,0

      (Found "Zara 1200,0\nAna 1200,0\nMaria 800,0" instead of "Zara 1200.0\nAna 1200.0\nMaria 800.0") */
    }

    public String getNume() {
        return nume;
    }

    public double getSalariu() {
        return salariu;
    }

    @Override
    public int compareTo(Angajat altul) {
        return Double.compare(this.salariu, altul.salariu);
    }
}