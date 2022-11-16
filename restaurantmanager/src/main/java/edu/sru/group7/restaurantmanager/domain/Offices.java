package edu.sru.group7.restaurantmanager.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Offices extends Locations {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	private long id;
	
	private String address;
	
	private String state;
	
	private String zipcode;
	
	private String city;
	
	@OneToMany(cascade=CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "office")
    private List<Admins> admin = new ArrayList<>();

	public Offices() {
		super();
	}
	
	public Offices(String address, String zipcode, String city, String state) {
		super();
		this.address = address;
		this.zipcode = zipcode;
		this.city = city;
		this.state = state;
	}
	
	public Offices(String address, String zipcode, String city, String state, List<Admins> admin) {
		super();
		this.address = address;
		this.zipcode = zipcode;
		this.city = city;
		this.state = state;
		this.admin = admin;
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

	public List<Admins> getAdmin() {
		return admin;
	}

	public void setAdmin(List<Admins> admin) {
		this.admin = admin;
	}
	
	@Override
	public String toString() {
		return "id: " + id;
	}
	
}