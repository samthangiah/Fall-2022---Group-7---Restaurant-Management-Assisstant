package edu.sru.group7.restaurantmanager.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String date;
    private float price;
    private String instructions;
    
    @ManyToOne
	@JoinColumn(name="restaurant")
    private Restaurants restaurant;
    
    @ManyToMany()
    @JoinTable(name = "order_items",
    joinColumns = {
            @JoinColumn(name = "order_id")},
    inverseJoinColumns = {
            @JoinColumn(name = "menu_id")})
    private Set<Menu> items = new HashSet<Menu>();
    
    @ManyToOne
	@JoinColumn(name="customer_id")
    private Customers customer_id;
    
    public Orders() {
    }
    
    public Orders(long id, String date, float price, Customers customer_id, Set<Menu> items, String instructions, Restaurants restaurant) {
        this.id = id;
        this.date = date;
        this.price = price;
        this.customer_id = customer_id;
        this.items = items;
        this.instructions = instructions;
        this.restaurant = restaurant;
        
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Set<Menu> getItems() {
		return items;
	}

	public void setItems(Set<Menu> items) {
		this.items = items;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public Restaurants getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurants restaurant) {
		this.restaurant = restaurant;
	}

	public Customers getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Customers customer_id) {
		this.customer_id = customer_id;
	}

	
    
}