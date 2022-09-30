package edu.sru.group7.restaurantmanager.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Restaurants extends Locations {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	private long id;
	
	private String address;
	
	private String state;
	
	private String zipcode;
	
	private String city;
	
	public Restaurants(String address, String zipcode, String city, String state) {
		super();
		this.address = address;
		this.zipcode = zipcode;
		this.city = city;
		this.state = state;
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

	
	
	
}