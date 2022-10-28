package edu.sru.group7.restaurantmanager.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Menu {
    @Id
    private long id;
    private String name;
    private String entree;
    private String sides;
    private float price;
    private boolean availability;
    private int quantity;
    
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "menu")
    private List<Ingredients> ingredients;
    
    public Menu() {
    }
    
    public Menu(long id, String name, String entree, String sides, float price, boolean availability, int quantity) {
        this.id = id;
        this.name = name;
        this.entree = entree;
        this.sides = sides;
        this.price = price;
        this.availability = availability;
        this.quantity = quantity;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public long getId() {
        return id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setEntree(String entree) {
        this.entree = entree;
    }
    
    public String getEntree() {
        return entree;
    }
    
    public void setSides(String sides) {
        this.sides = sides;
    }
    
    public String getSides() {
        return sides;
    }
    
    public void setPrice(float price) {
        this.price = price;
    }
    
    public float getPrice() {
        return price;
    }
    
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
    
    public boolean getAvailability() {
        return availability;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public int getQuantity() {
        return quantity;
    }

	@Override
	public String toString() {
		return "[name=" + name + ", price=" + price + "]";
	}
    
    
}
