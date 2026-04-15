package com.pao.project.fooddelivery.model;

public class Sofer extends Persoana {

    private boolean disponibilitate;
    private String nrMasina;

    public Sofer(String email, int id, String nume, String telefon, String nrMasina, boolean disponibilitate) {
        super(email, id, nume, telefon);
        this.nrMasina = nrMasina;
        this.disponibilitate = disponibilitate;
    }

    public boolean getDisponibilitate()
    {
        return disponibilitate;
    }

    public String getNrMasina()
    {
        return nrMasina;
    }

    public void setDisponibilitate(boolean other)
    {
        this.disponibilitate = other;
    }

    @Override
    public String toString()
    {
        String disponibilitateStr;
        if (disponibilitate == false)
        {
            disponibilitateStr = "Nu";
        }
        else
        {
            disponibilitateStr = "Da";
        }

        return "Sofer cu id = " + id + " cu numele " + nume +" ,telefon: " + telefon + " email: " + email + " , numar masina: " + nrMasina 
        + " , disponibil: " + disponibilitateStr;
    }

   
}