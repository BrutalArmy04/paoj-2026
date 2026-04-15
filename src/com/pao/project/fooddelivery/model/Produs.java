package com.pao.project.fooddelivery.model;

public abstract class Produs {

    protected int id;
    protected String nume;
    protected double pret;
    protected String descriere;
    protected boolean disponibilitate;
    
    public Produs(int id, String nume, double pret, String descriere, boolean disponibilitate) {
        this.id = id;
        this.nume = nume;
        this.pret = pret;
        this.descriere = descriere;
        this.disponibilitate = disponibilitate;
    }

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public double getPret() {
        return pret;
    }

    public String getDescriere() {
        return descriere;
    }

    public boolean isDisponibilitate() {
        return disponibilitate;
    }

    @Override
    public boolean equals(Object obj) {
        {
        if (obj instanceof Produs)
        {
            Produs other = (Produs) obj;
            return this.id == other.id;
        }
        return false;
    }
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return "[id=" + id + ", nume=" + nume + ", pret=" + pret + ", descriere=" + descriere
                + ", disponibilitate=" + disponibilitate + "]";
    }

    

   
    
}
