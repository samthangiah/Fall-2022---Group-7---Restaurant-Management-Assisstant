package edu.sru.group7.restaurantmanager.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Shipping POJO
 */
@Entity
public class Shipping {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "warehouse_id")
	private Warehouses warehouse_id;
	
	@ManyToOne
	@JoinColumn(name = "restaurant_id")
	private Restaurants restaurant_id;
	
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "manager_id")
	private Managers manager_id;
	
	@ManyToOne
	@JoinColumn(name = "warehousemanager_id")
	private WarehouseManager warehousemanager_id;

	private String ingredient;
	
	private Integer quantity;
	
	private String explanation;
	
	private String date;
	
	private String time;

	/**
	 * Parameterized Constructor
	 * @param id
	 * @param warehouse_id
	 * @param restaurant_id
	 * @param status
	 * @param manager_id
	 * @param warehousemanager_id
	 * @param ingredient
	 * @param quantity
	 * @param explanation
	 */
	public Shipping(long id, Warehouses warehouse_id, Restaurants restaurant_id, String status, Managers manager_id,
			WarehouseManager warehousemanager_id, String ingredient, Integer quantity, String explanation) {
		super();
		this.id = id;
		this.warehouse_id = warehouse_id;
		this.restaurant_id = restaurant_id;
		this.status = status;
		this.manager_id = manager_id;
		this.warehousemanager_id = warehousemanager_id;
		this.ingredient = ingredient;
		this.quantity = quantity;
		this.explanation = explanation;
	}

	/**
	 * Default Constructor
	 */
	public Shipping() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Warehouses getWarehouse_id() {
		return warehouse_id;
	}

	public void setWarehouse_id(Warehouses warehouse_id) {
		this.warehouse_id = warehouse_id;
	}

	public Restaurants getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(Restaurants restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Managers getManager_id() {
		return manager_id;
	}

	public void setManager_id(Managers manager_id) {
		this.manager_id = manager_id;
	}

	public WarehouseManager getWarehousemanager_id() {
		return warehousemanager_id;
	}

	public void setWarehousemanager_id(WarehouseManager warehousemanager_id) {
		this.warehousemanager_id = warehousemanager_id;
	}

	public String getIngredient() {
		return ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	
	
	
}
