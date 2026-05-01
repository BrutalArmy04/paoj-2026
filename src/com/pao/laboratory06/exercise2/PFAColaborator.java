package com.pao.laboratory06.exercise2;

import java.util.Locale;
import java.util.Scanner;

public class PFAColaborator extends Colaborator implements PersoanaFizica {
    private double cheltuieliLunare;

    @Override
    public void citeste(Scanner in) {
        this.nume = in.next();
        this.prenume = in.next();
        this.venitBrutLunar = in.nextDouble();
        this.cheltuieliLunare = in.nextDouble();
    }

    @Override
    public void afiseaza() {
        System.out.printf(Locale.US, "PFA: %s %s, venit net anual: %.2f lei\n", nume, prenume, calculeazaVenitNetAnual());
    }

    @Override
    public String tipContract() {
        return "PFA";
    }

    @Override
    public double calculeazaVenitNetAnual() {
        double venitNet = (venitBrutLunar - cheltuieliLunare) * 12;
        double impozit = 0.10 * venitNet;
        double salMinim = 48600;
        
        double cass;
        if (venitNet < 6 * salMinim) {
            cass = 0.10 * (6 * salMinim);
        } else if (venitNet <= 72 * salMinim) {
            cass = 0.10 * venitNet;
        } else {
            cass = 0.10 * (72 * salMinim);
        }
        
        double cas;
        if (venitNet < 12 * salMinim) {
            cas = 0;
        } else if (venitNet <= 24 * salMinim) {
            cas = 0.25 * (12 * salMinim);
        } else {
            cas = 0.25 * (24 * salMinim);
        }
        
        return venitNet - impozit - cass - cas;
    }

    @Override
    public TipColaborator getTip() {
        return TipColaborator.PFA;
    }
}