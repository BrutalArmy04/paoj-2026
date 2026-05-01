package com.pao.project.fooddelivery.model;

import java.util.Set;

public class Preparat extends Produs{
    
    private double gramaj;
    private Set<String> alergeni;
    private TipProdus tipProdus; 
    private int calorii;
    private int estimatedCookingTime;

    public Preparat(int id, String nume, double pret, String descriere, boolean disponibilitate, double gramaj,
            Set<String> alergeni, TipProdus tipProdus, int calorii, int estimatedCookingTime) {
        super(id, nume, pret, descriere, disponibilitate);
        this.gramaj = gramaj;
        this.alergeni = alergeni;
        this.tipProdus = tipProdus;
        this.calorii = calorii;
        this.estimatedCookingTime = estimatedCookingTime;
    }

    public double getGramaj() {
        return gramaj;
    }

    public void setGramaj(double gramaj) {
        this.gramaj = gramaj;
    }

    public Set<String> getAlergeni() {
        return alergeni;
    }

    public void setAlergeni(Set<String> alergeni) {
        this.alergeni = alergeni;
    }

    public TipProdus getTipProdus() {
        return tipProdus;
    }

    public void setTipProdus(TipProdus tipProdus) {
        this.tipProdus = tipProdus;
    }

    public int getCalorii() {
        return calorii;
    }

    public void setCalorii(int calorii) {
        this.calorii = calorii;
    }

    public int getEstimatedCookingTime() {
        return estimatedCookingTime;
    }

    public void setEstimatedCookingTime(int estimatedCookingTime) {
        this.estimatedCookingTime = estimatedCookingTime;
    }

    @Override
    public String toString() {
        return "Preparat " + super.toString() + "gramaj=" + gramaj + ", alergeni="
                + alergeni + "tipProdus=" + tipProdus  + ", calorii=" + calorii + ", estimatedCookingTime=" + estimatedCookingTime + "]";
    }

}