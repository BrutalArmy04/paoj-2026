package com.pao.laboratory06.exercise3;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Vezi Readme.md pentru cerințe
        System.out.println("TVA curent: " + ConstanteFinanciare.TVA.getValoare() + "\n");

        Inginer i1 = new Inginer("Popescu", "Ion", "0711111111", 8500);
        Inginer i2 = new Inginer("Ionescu", "Andrei", "0722222222", 9500);
        Inginer i3 = new Inginer("Dumitru", "Mihai", "0733333333", 7500);

        Inginer[] ingineri = {i1, i2, i3};

        Arrays.sort(ingineri);
        for (Inginer ing : ingineri) {
            System.out.println(ing);
        }
        System.out.println();

        Arrays.sort(ingineri, new ComparatorInginerSalariu());
        for (Inginer ing : ingineri) {
            System.out.println(ing);
        }
        System.out.println();

        PlataOnline refInginer = i1;
        refInginer.autentificare("ionp", "parola123");
        System.out.println("Sold inginer: " + refInginer.consultareSold());
        refInginer.efectuarePlata(500);
        System.out.println("Sold după plata: " + refInginer.consultareSold() + "\n");

        PlataOnlineSMS refPJ = new PersoanaJuridica("Tech", "SRL", "0744444444");
        refPJ.autentificare("tech_srl", "securePass");
        refPJ.trimiteSMS("Plata a fost efectuata cu succes!");
        refPJ.trimiteSMS("");
        System.out.println("SMS-uri stocate: " + ((PersoanaJuridica) refPJ).getSmsTrimise() + "\n");

        PlataOnlineSMS refPjFaraTelefon = new PersoanaJuridica("FaraTel", "SRL", null);
        boolean trimis = refPjFaraTelefon.trimiteSMS("Test");
        System.out.println("SMS trimis pe entitate fara telefon: " + trimis + "\n");

        try {
            refInginer.trimiteSMS("Test SMS Inginer");
        } catch (UnsupportedOperationException e) {
            System.out.println("Eroare asteptata (Inginer SMS): " + e.getMessage());
        }

        try {
            refInginer.autentificare(null, "parola");
        } catch (IllegalArgumentException e) {
            System.out.println("Eroare asteptata (User null): " + e.getMessage());
        }
    }
}


/*Inginer{Popescu Ion, salariu=8500.0}

Inginer{Ionescu Andrei, salariu=9500.0}
Inginer{Popescu Ion, salariu=8500.0}
Inginer{Dumitru Mihai, salariu=7500.0}

Sold inginer: 5000.0
Sold dup? plata: 4500.0

SMS-uri stocate: [Plata a fost efectuata cu succes!]

SMS trimis pe entitate fara telefon: false

Eroare asteptata (Inginer SMS): Entitatea nu are capabilitate SMS.
Eroare asteptata (User null): User sau parola invalide. */