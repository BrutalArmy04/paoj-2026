package com.pao.project.fooddelivery.model;

public class Bautura extends Produs{
    
    private int volum;
    private boolean alcoolic;
    private boolean carbogazoasa;
    private TemperaturaServire temperaturaServire;
    
    public Bautura(int id, String nume, double pret, String descriere, boolean disponibilitate, int volum, boolean alcoolic,
            boolean carbogazoasa, TemperaturaServire temperaturaServire) {
        super(id, nume, pret, descriere, disponibilitate);
        this.volum = volum;
        this.alcoolic = alcoolic;
        this.carbogazoasa = carbogazoasa;
        this.temperaturaServire = temperaturaServire;
    }

    public int getVolum() {
        return volum;
    }

    public void setVolum(int volum) {
        this.volum = volum;
    }

    public boolean isAlcoolic() {
        return alcoolic;
    }

    public void setAlcoolic(boolean alcoolic) {
        this.alcoolic = alcoolic;
    }

    public boolean isCarbogazoasa() {
        return carbogazoasa;
    }

    public void setCarbogazoasa(boolean carbogazoasa) {
        this.carbogazoasa = carbogazoasa;
    }

    public TemperaturaServire getTemperaturaServire() {
        return temperaturaServire;
    }

    public void setTemperaturaServire(TemperaturaServire temperaturaServire) {
        this.temperaturaServire = temperaturaServire;
    }

    @Override
    public String toString() {
        return "Bautura "+ super.toString() + "volum=" + volum + ", alcoolic=" + alcoolic
                + ", carbogazoasa=" + carbogazoasa  + ", temperaturaServire="
                + temperaturaServire;
    }


    

    
}
