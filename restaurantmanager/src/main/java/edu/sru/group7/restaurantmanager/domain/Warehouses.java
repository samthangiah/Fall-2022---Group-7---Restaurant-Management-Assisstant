package edu.sru.group7.restaurantmanager.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Warehuose POJO
 */
@Entity
public class Warehouses extends Locations {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	private long id;
	
	private String address;
	
	private String state;
	
	private String zipcode;
	
	private String city;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "warehouse_id")
	private List<Inventory> inventory = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "warehouse_id")
	private List<Shipping> shipments = new ArrayList<>();
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "warehouse")
    private List<WarehouseManager> manager = new ArrayList<>();
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "warehouse")
    private List<WarehouseEmployees> employees = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="admin_id")
	private Admins admin;
	
	/**
	 * Parameterized Constructor
	 * @param address
	 * @param zipcode
	 * @param city
	 * @param state
	 * @param admin
	 */
	public Warehouses(String address, String zipcode, String city, String state, Admins admin) {
		super();
		this.address = address;
		this.zipcode = zipcode;
		this.city = city;
		this.state = state;
		this.admin = admin;
	}

	/**
	 * Default Constructor
	 */
	public Warehouses() {
		super();
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
		
	}

	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public void setAddress(String address) {
		this.address = address;
		
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<Shipping> getShipments() {
		return shipments;
	}

	public void setShipments(List<Shipping> shipments) {
		this.shipments = shipments;
	}

	public List<Inventory> getInventory() {
		return inventory;
	}

	public void setInventory(List<Inventory> inventory) {
		this.inventory = inventory;
	}

	public List<WarehouseManager> getManager() {
		return manager;
	}

	public void setManager(List<WarehouseManager> manager) {
		this.manager = manager;
	}

	public List<WarehouseEmployees> getEmployees() {
		return employees;
	}

	public void setEmployees(List<WarehouseEmployees> employees) {
		this.employees = employees;
	}

	public Admins getAdmin() {
		return admin;
	}

	public void setAdmin(Admins admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "Warehouse ID: " + id + " Address: " + address;
	}
	
	
}
