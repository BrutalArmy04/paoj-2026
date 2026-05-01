package com.pao.laboratory09.exercise3;

import java.util.Locale;

public class ProcessorThread implements Runnable {
    private final CoadaTranzactii banda;
    
    public volatile boolean activ = true; 

    public ProcessorThread(CoadaTranzactii banda) {
        this.banda = banda;
    }

    @Override
    public void run() {
        try {
            while (activ || !banda.isGoala()) {
                
                if (!activ && banda.isGoala()) {
                    break;
                }

                TranzactieMini t = banda.extrage();
                
                if (t != null) {
                    System.out.printf(Locale.US, "[Processor] Factura #%d - %.2f RON | %s\n", 
                                      t.getId(), t.getSuma(), t.getData());
                    
                    Thread.sleep(80); 
                }
            }
        } catch (InterruptedException e) {
        }
    }
}