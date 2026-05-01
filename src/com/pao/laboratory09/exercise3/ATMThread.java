package com.pao.laboratory09.exercise3;

import java.util.Random;

public class ATMThread extends Thread {
    private final int idAtm;
    private final CoadaTranzactii banda;
    private final Random random = new Random();

    public ATMThread(int idAtm, CoadaTranzactii banda) {
        this.idAtm = idAtm;
        this.banda = banda;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 4; i++) {
                int idTranzactie = (idAtm * 100) + i;
                double suma = 10 + (990 * random.nextDouble());
                TranzactieMini t = new TranzactieMini(idTranzactie, suma, "2024-05-01");

                banda.adauga(t, idAtm);

                Thread.sleep(50); 
            }
        } catch (InterruptedException e) {
            System.out.println("ATM-" + idAtm + " a fost intrerupt.");
        }
    }
}