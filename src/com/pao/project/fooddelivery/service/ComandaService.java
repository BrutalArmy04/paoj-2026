package com.pao.project.fooddelivery.service;

import java.util.List;
import java.util.TreeSet;

import com.pao.project.fooddelivery.exception.SoferIndisponibilException;
import com.pao.project.fooddelivery.model.Client;
import com.pao.project.fooddelivery.model.Comanda;
import com.pao.project.fooddelivery.model.Produs;
import com.pao.project.fooddelivery.model.Restaurant;
import com.pao.project.fooddelivery.model.Sofer;
import com.pao.project.fooddelivery.model.Status;



public class ComandaService {
    
    private static ComandaService instance;
    private int contorComenzi = 1;
    public static ComandaService getInstance()
    {
        if (instance == null)
        {
            instance = new ComandaService();
        }
        return instance;
    }

    private TreeSet<Comanda> listaComenzi;

    private ComandaService() {
        this.listaComenzi = new TreeSet<Comanda>();
    }


    public void plaseazaComanda(Client client, Restaurant restaurant, List<Produs> produse)
    {
        double sumaTotala = 0;

        for (Produs produs: produse)
        {
            sumaTotala += produs.getPret();
        }

        sumaTotala += restaurant.getTaxaLivrare();
        int newID = contorComenzi++;
        Comanda comanda = new Comanda(newID, client, restaurant, produse, null, Status.PLASATA, sumaTotala, null);
        listaComenzi.add(comanda);
    }
    
    public void afiseazaComenziClient(Client client)
    {
        for (Comanda comanda : listaComenzi)
        {
            if (comanda.getClient().equals(client))
            {
                System.out.println(comanda);
            }
        }
    }

    private Comanda cautaComanda(int id)
    {
        for (Comanda comanda : listaComenzi)
        {
            if (comanda.getId() == id)
                return comanda;
        }
        System.out.println("Comanda cu ID-ul " + id + " nu exista!");
        return null;
    }


    public void updateStatusComanda(int idComanda, Status statusNou)    //pt fallback
    {
        Comanda comanda = cautaComanda(idComanda);
        if (comanda!=null)
{
        comanda.setStatus(statusNou);
        System.out.println("Statusul comenzii " + idComanda + " a fost actualizat."); 
}
    }

    public void preparaComanda(int idComanda)
    {
        Comanda comanda = cautaComanda(idComanda);
        if (comanda!=null && comanda.getStatus() == Status.PLASATA)
        {
            comanda.setStatus(Status.IN_PREPARARE);
        }
    }


    public void asigneazaSofer(int idComanda, Sofer sofer) throws SoferIndisponibilException
    {
        Comanda comanda = cautaComanda(idComanda);
        if (comanda !=null)
        if (sofer.getDisponibilitate() == false)
        {
            throw new SoferIndisponibilException("Soferul " + sofer.getNume() + " este deja ocupat!");
        }
        else
            {
            comanda.setSofer(sofer);
            comanda.setStatus(Status.IN_LIVRARE);
            sofer.setDisponibilitate(false);
           }
    }

    public void finalizeazaLivrarea(int idComanda)
    {
        Comanda comanda = cautaComanda(idComanda);
        if (comanda!=null && comanda.getSofer()!=null){
        comanda.setStatus(Status.LIVRATA);
        comanda.getSofer().setDisponibilitate(true);
    }}
}
