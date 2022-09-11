package edu.sru.group7.restaurantmanager.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String entree;
    private String sides;
    private float price;
    private boolean availability;
    private int quantity;
    
    public Menu() {
    }
    
    public Menu(long id, String name, String entree, String sides, float price, boolean availability, int quantity) {
        this.id = id;
        this.name = name;
        this.entree = entree;
        this.sides = sides;
        this.price = price;
        this.availability;
        this.quantity;
    }
    
    public void SetId(long id) {
        this.id = id;
    }
    
    public long GetId() {
        return id;
    }
    
    public void SetName(String name) {
        this.name = name;
    }
    
    public String GetName() {
        return name;
    }
    
    public void SetEntree(String entree) {
        this.entree = entree;
    }
    
    public String GetEntree() {
        return entree;
    }
    
    public void SetSides(String sides) {
        this.sides = sides;
    }
    
    public String GetSides() {
        return sides;
    }
    
    public void SetPrice(float price) {
        this.price = price;
    }
    
    public float GetPrice() {
        return price;
    }
    
    public void SetAvailability(boolean availability) {
        this.availability = availability;
    }
    
    public boolean GetAvailability() {
        return availability;
    }
    
    public void SetQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public int GetQuantity() {
        return quantity;
    }
}