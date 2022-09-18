package edu.sru.group7.restaurantmanager.domain;

abstract class Locations {

    private long id;
	
	private String address;

	abstract long getId();
	
	abstract void setId(long id);
	
	abstract String getAddress();
	
	abstract void setAddress(String address);
	
	
}