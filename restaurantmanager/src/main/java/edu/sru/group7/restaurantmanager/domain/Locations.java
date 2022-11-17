package edu.sru.group7.restaurantmanager.domain;

public abstract class Locations {

    private long id;
	
	private String address;
	
	private String state;
	
	private String zipcode;
	
	private String city;

	abstract long getId();
	
	abstract void setId(long id);
	
	abstract String getAddress();
	
	abstract void setAddress(String address);

	abstract String getState();

	abstract void setState(String state);

	abstract String getZipcode();

	abstract void setZipcode(String zipcode);

	abstract String getCity();

	abstract void setCity(String city);
}