package com.pao.project.fooddelivery.model;


public class Client extends Persoana{
    private Adresa adresaLivrare;

    public Client(String email, int id, String nume, String telefon, Adresa adresaLivrare) {
        super(email, id, nume, telefon);
        this.adresaLivrare = adresaLivrare;
    }

    public Adresa getAdresaLivrare()
    {
        return adresaLivrare;
    }
    
    @Override
    public String toString()
    {
        return super.toString() + " cu adresa: " + adresaLivrare;
    }
}
