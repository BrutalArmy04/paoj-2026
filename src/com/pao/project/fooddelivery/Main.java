package com.pao.project.fooddelivery;

import com.pao.project.fooddelivery.model.*;
import com.pao.project.fooddelivery.service.*;
import com.pao.project.fooddelivery.exception.*;

import java.util.*;

public class Main {
    public static void main(String[] args) throws SoferIndisponibilException {
        // Obținem instanțele serviciilor (Singleton)
        RestaurantService restaurantService = RestaurantService.getInstance();
        ComandaService comandaService = ComandaService.getInstance();
        Adresa adresaRest = new Adresa("Strada Victoriei", 10, "Etaj 1");
        Adresa adresaClient = new Adresa("Bulevardul Unirii", 5, "Ap 12");
        Client client1 = new Client("andrei@email.com", 1, "Andrei Popescu", "0722111222", adresaClient);
        Sofer sofer1 = new Sofer("vasile@delivery.com", 1, "Vasile Soferu", "0733444555", "B-99-TAX", true);
        Restaurant rest1 = new Restaurant("Pizza Mania", adresaRest, 4.8, 15.0, true, new HashMap<>());
        restaurantService.addRestaurant(rest1);
        Set<String> alergeniPizza = new HashSet<>(Arrays.asList("gluten", "lactoza"));
        Preparat pizza = new Preparat(101, "Pizza Margherita", 35.0, "Sos rosii, mozzarella", true, 450.0, alergeniPizza, TipProdus.VEGETARIAN, 800, 20);
        Bautura cola = new Bautura(201, "Coca Cola", 8.0, "Bautura racoritoare", true, 500, false, true, TemperaturaServire.RECE);
        restaurantService.addProdus(rest1, "Pizza", pizza);
        restaurantService.addProdus(rest1, "Bauturi", cola);
        System.out.println("\nCăutăm restaurantul Pizza Mania...");
        Restaurant rGasit = restaurantService.searchRestaurant("Pizza Mania");

        if (rGasit != null) {
            System.out.println("Meniul pentru " + rGasit.getNume() + ": " + rGasit.getMeniu());
            List<Produs> cosCumparaturi = new ArrayList<>(Arrays.asList(pizza, cola));
            System.out.println("\nClientul plaseaza o comandă...");
            comandaService.plaseazaComanda(client1, rGasit, cosCumparaturi);
            System.out.println("\nComenzile lui " + client1.getNume() + ":");
            comandaService.afiseazaComenziClient(client1);
            System.out.println("\nRestaurantul începe prepararea comenzii...");
            comandaService.preparaComanda(1);
            System.out.println("\nAsignam un șofer pentru livrare...");
            try {
                comandaService.asigneazaSofer(1, sofer1);
                System.out.println("Sfer asignat cu succes. Status actualizat.");
            } catch (Exception e) { 
                throw new SoferIndisponibilException("Șoferul " + sofer1.getNume() + " este deja ocupat!");
            }
            System.out.println("\nIncercam sa asignam acelasi sofer la o altă comanda...");
            comandaService.plaseazaComanda(client1, rGasit, new ArrayList<>(Arrays.asList(cola)));
            try {
                comandaService.asigneazaSofer(2, sofer1); 
            } catch (Exception e) {
                System.out.println("Sistemul a blocat asignarea: " + e.getMessage());
            }
            System.out.println("\nLivrarea a ajuns la destinatie...");
            comandaService.finalizeazaLivrarea(1);
            System.out.println("Status final sofer " + sofer1.getNume() + " disponibil: " + sofer1.getDisponibilitate());
        }

    }
}