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
 * Restaurants POJO
 */
@Entity
public class Restaurants extends Locations {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	private long id;
	
	private String address;
	
	private String state;
	
	private String zipcode;
	
	private String city;
	
	private float sales;
	
	private float profits;
	
	@ManyToOne
	@JoinColumn(name="admin_id")
	private Admins admin;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "location")
	private List<Customers> customers = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "restaurant")
	private List<Orders> order = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "restaurant_id")
	private List<Inventory> inventory = new ArrayList<>();
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Servers> servers = new ArrayList<>();
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Managers> managers = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "restaurant_id")
	private List<Shipping> shipments = new ArrayList<>();
	
	/**
	 * Parameterized Constructor
	 * @param address
	 * @param zipcode
	 * @param city
	 * @param state
	 * @param admin
	 */
	public Restaurants(String address, String zipcode, String city, String state, Admins admin) {
		super();
		this.address = address;
		this.zipcode = zipcode;
		this.city = city;
		this.state = state;
		this.admin = admin;
		this.sales = 0.00F;
		this.profits = 0.00F;
	}

	/**
	 * Default Constructor
	 */
	public Restaurants() {
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
	
	public Admins getAdmin() {
		return admin;
	}

	public void setAdmin(Admins admins) {
		this.admin = admins;
	}

	public List<Servers> getServers() {
		return servers;
	}

	public void setServers(List<Servers> servers) {
		this.servers = servers;
	}

	public List<Managers> getManagers() {
		return managers;
	}

	public void setManagers(List<Managers> managers) {
		this.managers = managers;
	}
	
	public float getSales() {
		return sales;
	}

	public void setSales(float sales) {
		this.sales = sales;
	}

	public float getProfits() {
		return profits;
	}

	public void setProfits(float profits) {
		this.profits = profits;
	}

	public List<Shipping> getShipments() {
		return shipments;
	}

	public void setShipments(List<Shipping> shipments) {
		this.shipments = shipments;
	}

	public List<Orders> getOrder() {
		return order;
	}

	public void setOrder(List<Orders> order) {
		this.order = order;
	}
	
	@Override
	public String toString() {
		return "Restaurant ID: " + id + " Address: " + address;
	}
}