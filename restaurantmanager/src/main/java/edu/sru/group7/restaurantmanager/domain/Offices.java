package edu.sru.group7.restaurantmanager.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Offices extends Locations {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	private long id;
	
	private String address;
	
	public Offices(long id, String address) {
		super();
		this.id = id;
		this.address = address;
	}

	public Offices() {
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

	
	
	
}
