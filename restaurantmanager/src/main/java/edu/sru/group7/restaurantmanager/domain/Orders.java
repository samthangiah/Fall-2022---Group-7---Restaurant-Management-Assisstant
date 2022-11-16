package edu.sru.group7.restaurantmanager.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 * POJO that holds all Orders
 */
@Entity
public class Orders {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String date;
    private float price;
    private String instructions;
    private String status;
    
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
    
    /**
     * Default Orders Constructor
     */
    public Orders() {
    }
    
    /**
     * Orders Constructor with parameters
     * @param id
     * @param date
     * @param price
     * @param customer_id
     * @param items
     * @param instructions
     * @param restaurant
     */
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

	/**
	 * @return Restaurant belonging to this Order
	 */
	public Restaurants getRestaurant() {
		return restaurant;
	}

	/**
	 * @param restaurant belonging to this Order
	 */
	public void setRestaurant(Restaurants restaurant) {
		this.restaurant = restaurant;
	}

	/**
	 * @return Customer of the Order
	 */
	public Customers getCustomer_id() {
		return customer_id;
	}

	/**
	 * @param customer_id Customer of the Order
	 */
	public void setCustomer_id(Customers customer_id) {
		this.customer_id = customer_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
    
}