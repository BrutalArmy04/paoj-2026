package com.pao.laboratory09.exercise3;

import java.util.LinkedList;
import java.util.Queue;

public class CoadaTranzactii {
    private final Queue<TranzactieMini> coada = new LinkedList<>();
    private final int capacitateMaxima = 5;

    public synchronized void adauga(TranzactieMini t, int atmId) throws InterruptedException {
        while (coada.size() == capacitateMaxima) {
            System.out.println("[ATM-" + atmId + "] astept loc pe banda...");
            wait();
        }
        
        coada.add(t);
        System.out.println("[ATM-" + atmId + "] trimite: Tranzactie #" + t.getId() + " - " + t.getSuma() + " RON");
        
        notifyAll(); 
    }

    public synchronized TranzactieMini extrage() throws InterruptedException {
        while (coada.isEmpty()) {
            wait();
        }
        
        TranzactieMini t = coada.poll();
        
        notifyAll(); 
        
        return t;
    }

    public synchronized void trezesteToateFirele() {
        notifyAll();
    }
    
    public synchronized boolean isGoala() {
        return coada.isEmpty();
    }
}