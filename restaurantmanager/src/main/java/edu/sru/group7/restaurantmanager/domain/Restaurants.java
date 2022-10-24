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

@Entity
public class Restaurants extends Locations {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	private long id;
	
	private String address;
	
	private String state;
	
	private String zipcode;
	
	private String city;
	
	@ManyToOne
	@JoinColumn(name="admin_id")
	private Admins admin;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "restaurant")
	private List<Orders> order = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "restaurant_id")
	private List<Inventory> inventory = new ArrayList<>();
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Servers> servers = new ArrayList<>();
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Managers> managers = new ArrayList<>();
	
	public Restaurants(String address, String zipcode, String city, String state, Admins admin) {
		super();
		this.address = address;
		this.zipcode = zipcode;
		this.city = city;
		this.state = state;
		this.admin = admin;
	}

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

	@Override
	public String toString() {
		return "id: " + id;
	}
	
	
	
}