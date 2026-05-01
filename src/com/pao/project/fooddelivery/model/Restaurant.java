package com.pao.project.fooddelivery.model;

import java.util.List;
import java.util.Map;

public class Restaurant {

    private String nume;
    private Adresa adresa;
    private double rating;
    private double taxaLivrare;
    private boolean status;
    private Map<String, List<Produs>> meniu;

    public Restaurant(String nume, Adresa adresa, double rating, double taxaLivrare, boolean status,
            Map<String, List<Produs>> meniu) {
        this.nume = nume;
        this.adresa = adresa;
        this.rating = rating;
        this.taxaLivrare = taxaLivrare;
        this.status = status;
        this.meniu = meniu;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Adresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getTaxaLivrare() {
        return taxaLivrare;
    }

    public void setTaxaLivrare(double taxaLivrare) {
        this.taxaLivrare = taxaLivrare;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Map<String, List<Produs>> getMeniu() {
        return meniu;
    }

    public void setMeniu(Map<String, List<Produs>> meniu) {
        this.meniu = meniu;
    }

    @Override
    public String toString() {
        return "Restaurant [nume=" + nume + ", adresa=" + adresa + ", rating=" + rating + ", status=" + status
                + ", meniu=" + meniu + "]";
    }

    
    
    
}
