package com.pao.laboratory06.exercise3;

import java.util.ArrayList;
import java.util.List;

public class PersoanaJuridica extends Persoana implements PlataOnlineSMS {
    private List<String> smsTrimise;
    private double sold = 25000.0;

    public PersoanaJuridica(String nume, String prenume, String telefon) {
        super(nume, prenume, telefon);
        this.smsTrimise = new ArrayList<>();
    }

    @Override
    public void autentificare(String user, String parola) {
        if (user == null || user.trim().isEmpty() || parola == null || parola.trim().isEmpty()) {
            throw new IllegalArgumentException("User sau parola invalide.");
        }
    }

    @Override
    public double consultareSold() {
        return sold;
    }

    @Override
    public boolean efectuarePlata(double suma) {
        if (suma > 0 && suma <= sold) {
            sold -= suma;
            return true;
        }
        return false;
    }

    @Override
    public boolean trimiteSMS(String mesaj) {
        if (this.telefon == null || this.telefon.trim().isEmpty()) {
            return false;
        }
        if (mesaj == null || mesaj.trim().isEmpty()) {
            return false;
        }
        smsTrimise.add(mesaj);
        return true;
    }

    public List<String> getSmsTrimise() {
        return smsTrimise;
    }

    @Override
    public String toString() {
        return "PersoanaJuridica{" + nume + " " + prenume + "}";
    }
}