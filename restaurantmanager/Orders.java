package edu.sru.group7.restaurantmanager.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String date;
    private float price;
    private String customer;
    private String items;
    private String instructions;
    private String location;
    
    public Orders() {
    }
    
    public Orders(long id, String date, float price, String customer, String items, String instructions, String location) {
        this.id = id;
        this.date = date;
        this.price = price;
        this.customer = customer;
        this.items = items;
        this.instructions = instructions;
        this.location = location;
    }
    
    public void SetId(long id) {
        this.id = id;
    }
    
    public long GetId() {
        return id;
    }
    
    public void SetDate(String date) {
        this.date = date;
    }
    
    public String GetDate() {
        return date;
    }
    
    public void SetPrice(float price) {
        this.price = price;
    }
    
    public float GetPrice() {
        return price;
    }
    
    public void SetCustomer(String customer) {
        this.customer = customer;
    }
    
    public String GetCustomer() {
        return customer;
    }
    
    public void SetItems(String items) {
        this.items = items;
    }
    
    public String GetItems() {
        return items;
    }
    
    public void SetInstructions(String instructions) {
        this.instructions = instructions;
    }
    
    public String GetInstructions() {
        return instructions;
    }
    
    public void SetLocation(String location) {
        this.location = location;
    }
    
    public String GetLocation() {
        return location;
    }
}