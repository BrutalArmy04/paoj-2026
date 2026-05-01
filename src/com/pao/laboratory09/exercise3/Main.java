package com.pao.laboratory09.exercise3;

public class Main {
    public static void main(String[] args) {
        // Vezi Readme.md pentru cerințe
        CoadaTranzactii banda = new CoadaTranzactii();

        ProcessorThread procesorRunnable = new ProcessorThread(banda);
        Thread firProcesor = new Thread(procesorRunnable);

        ATMThread atm1 = new ATMThread(1, banda);
        ATMThread atm2 = new ATMThread(2, banda);
        ATMThread atm3 = new ATMThread(3, banda);

        firProcesor.start();
        atm1.start();
        atm2.start();
        atm3.start();

        try {
            atm1.join();
            atm2.join();
            atm3.join();

            procesorRunnable.activ = false;
            
            banda.trezesteToateFirele(); 

            firProcesor.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Toate tranzactiile procesate. Total: 12");
    }
}
