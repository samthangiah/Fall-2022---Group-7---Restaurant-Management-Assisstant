package edu.sru.group7.restaurantmanager.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Inventory {
	
	@ManyToOne
	@JoinColumn(name = "restaurant_id")
	private Restaurants restaurant_id;
	
	@ManyToOne
	@JoinColumn(name = "warehouse_id")
	private Warehouses warehouse_id;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
    private String ingredient;
    
    private int quantity;

    /**
     * Warehouse Inventory Constructor
     * @param warehouse_id
     * @param id
     * @param ingredient
     * @param quantity
     */
	public Inventory(Warehouses warehouse_id, long id, String ingredient, int quantity) {
		super();
		this.warehouse_id = warehouse_id;
		this.id = id;
		this.ingredient = ingredient;
		this.quantity = quantity;
	}

	/**
	 * Restaurant Inventory Constructor
	 * @param restaurant_id
	 * @param id
	 * @param ingredient
	 * @param quantity
	 */
	public Inventory(Restaurants restaurant_id, long id, String ingredient, int quantity) {
		super();
		this.restaurant_id = restaurant_id;
		this.id = id;
		this.ingredient = ingredient;
		this.quantity = quantity;
	}

	/**
	 * Default Inventory Constructor
	 */
	public Inventory() {
	}

	/**
	 * Restaurant getter
	 * @return restaurant_id
	 */
	public Restaurants getRestaurant_id() {
		return restaurant_id;
	}

	/**
	 * Restaurant setter
	 * @param restaurant_id
	 */
	public void setRestaurant_id(Restaurants restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	/**
	 * Warehouse getter
	 * @return warehouse_id
	 */
	public Warehouses getWarehouse_id() {
		return warehouse_id;
	}

	/**
	 * Warehouse setter
	 * @param warehouse_id
	 */
	public void setWarehouse_id(Warehouses warehouse_id) {
		this.warehouse_id = warehouse_id;
	}

	/**
	 * Id getter
	 * @return id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Id setter
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Ingredient getter
	 * @return ingredient
	 */
	public String getIngredient() {
		return ingredient;
	}

	/**
	 * Ingredient setter
	 * @param ingredient
	 */
	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}

	/**
	 * Quantity getter
	 * @return quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Quantity setter
	 * @param quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
    
    

}
