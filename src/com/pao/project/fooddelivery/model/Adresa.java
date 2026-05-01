package com.pao.project.fooddelivery.model;


public class Adresa {
    private final String strada;
    private final int numar;
    private final String extraInfo;
    
    public Adresa(String strada, int numar, String extraInfo)
    {
        this.strada = strada;
        this.numar = numar;
        this.extraInfo = extraInfo;
    }

    public String getStrada()
    {
        return strada;
    }

    public int getNumar()
    {
        return numar;
    }

    public String getExtraInfo()
    {
        return extraInfo;
    }

    @Override
    public String toString()
    {
        return "Strada  " + strada + " numar " + numar + extraInfo;
    }
}
