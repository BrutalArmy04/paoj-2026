package com.pao.project.fooddelivery.model;

import java.util.List;

public class Comanda implements Comparable<Comanda>{
    
    private int id;
    private Client client;
    private Restaurant restaurant;
    private List<Produs> cos;
    private Sofer sofer;
    private Status status;
    private double pretTotal;
    private String timestamp;
    
    public Comanda(int id, Client client, Restaurant restaurant, List<Produs> cos, Sofer sofer, Status status,
            double sumaTotala, String timestamp) {
        this.id = id;
        this.client = client;
        this.restaurant = restaurant;
        this.cos = cos;
        this.sofer = sofer;
        this.status = status;
        this.pretTotal = sumaTotala;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Produs> getCos() {
        return cos;
    }

    public void setCos(List<Produs> cos) {
        this.cos = cos;
    }

    public Sofer getSofer() {
        return sofer;
    }

    public void setSofer(Sofer sofer) {
        this.sofer = sofer;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getPretTotal() {
        return pretTotal;
    }

    public void setPretTotal(double pretTotal) {
        this.pretTotal = pretTotal;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Comanda)
        {
            Comanda other = (Comanda) obj;
            return this.id == other.id;
        }
        return false;
    }

    @Override
    public int hashCode() {
    return Integer.hashCode(id);
}

    @Override
    public String toString() {
        return "Comanda [id=" + id + ", client=" + client + ", restaurant=" + restaurant + ", cos=" + cos + ", sofer="
                + sofer + ", status=" + status + ", pretTotal=" + pretTotal + ", timestamp=" + timestamp + "]";
    }

    @Override
    public int compareTo(Comanda altaComanda) {
        return Integer.compare(this.id, altaComanda.id);
    }
}