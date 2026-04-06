package com.pao.laboratory07.exercise3;

import java.util.Locale;

public final class ComandaGratuita extends Comanda {
    public ComandaGratuita(String nume, String client) {
        this.nume = nume;
        this.client = client;
    }

    @Override
    public double pretFinal() {
        return 0.0;
    }

    @Override
    public String tipComanda() {
        return "GIFT";
    }

    @Override
    public String descriere() {
        return String.format(Locale.US, "GIFT: %s, gratuit [%s] - client: %s", nume, stare, client);
    }
}