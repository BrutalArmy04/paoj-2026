package com.pao.project.fooddelivery.model;

public abstract class Persoana {
    protected int id;
    protected String nume, telefon, email;

    public Persoana(String email, int id, String nume, String telefon) {
        this.email = email;
        this.id = id;
        this.nume = nume;
        this.telefon = telefon;
    }

    public int getId()
    {
        return id;
    }

    public String getNume()
    {
        return nume;
    }

    public String getTelefon()
    {
        return telefon;
    }

    public String getEmail()
    {
        return email;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Persoana)
        {
            Persoana other = (Persoana) obj;
            return this.id == other.id;
        }
        return false;
    }

    @Override
    public int hashCode() {
    return Integer.hashCode(id);
}
    @Override
    public String toString()
    {
        return "Persoana cu id = " + id + " cu numele " + nume +" ,telefon: " + telefon + " email: " + email;
    }
    
    
}
