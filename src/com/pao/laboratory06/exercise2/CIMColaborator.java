package com.pao.laboratory06.exercise2;

import java.util.Locale;
import java.util.Scanner;

public class CIMColaborator extends Colaborator implements PersoanaFizica {
    private boolean bonus;

    @Override
    public void citeste(Scanner in) {
        this.nume = in.next();
        this.prenume = in.next();
        this.venitBrutLunar = in.nextDouble();
        
        String rand = in.nextLine().trim();
        if (rand.equals("DA")) {
            this.bonus = true;
        } else {
            this.bonus = false;
        }
    }

    @Override
    public void afiseaza() {
        System.out.printf(Locale.US, "CIM: %s %s, venit net anual: %.2f lei\n", nume, prenume, calculeazaVenitNetAnual());
    }

    @Override
    public String tipContract() {
        return "CIM";
    }

    @Override
    public boolean areBonus() {
        return bonus;
    }

    @Override
    public double calculeazaVenitNetAnual() {
        double net = venitBrutLunar * 12 * 0.55;
        if (areBonus()) {
            net *= 1.10;
        }
        return net;
    }

    @Override
    public TipColaborator getTip() {
        return TipColaborator.CIM;
    }
}