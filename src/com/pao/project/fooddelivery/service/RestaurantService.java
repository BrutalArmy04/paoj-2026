package com.pao.project.fooddelivery.service;

import java.util.ArrayList;

import com.pao.project.fooddelivery.model.Produs;
import com.pao.project.fooddelivery.model.Restaurant;

public class RestaurantService {

    private static RestaurantService instance;

    private ArrayList<Restaurant> restaurante;

    private RestaurantService() {
        this.restaurante = new ArrayList<>();
    }

    public static RestaurantService getInstance()
    {
        if (instance == null)
        {
            instance = new RestaurantService();
        }
        return instance;
    }

    public ArrayList<Restaurant> getRestaurante() {
        return restaurante;
    }

    public void addRestaurant(Restaurant newRestaurant)   
    {
        restaurante.add(newRestaurant);
        System.out.println("Restaurantul " + newRestaurant + " a fost adaugat");
    } 

    public Restaurant searchRestaurant(String numeRestaurant)
    {
        for (Restaurant restaurant : restaurante)
        {
            if (restaurant.getNume().equals(numeRestaurant))
            {
                System.out.println("Restaurantul a fost gasit");
                return restaurant;

            }
        }
        return null; // TODO: poate o exceptie

    }

    public void addProdus(Restaurant r, String categorie, Produs p)
    {
        var meniu = r.getMeniu();
        if(meniu.containsKey(categorie))
        {
            meniu.get(categorie).add(p);           
        }
        else
        {
            ArrayList<Produs> listaNoua = new ArrayList<Produs>();
            listaNoua.add(p);
            meniu.put(categorie, listaNoua);
        }
        r.setMeniu(meniu);
    }

    
}
