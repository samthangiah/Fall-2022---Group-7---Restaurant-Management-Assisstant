package edu.sru.group7.restaurantmanager.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Managers {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private String firstName;
    
    private String lastName;
    
    private String email;
    
    private String password;
    
    @ManyToOne
	@JoinColumn(name="restaurant_id")
	private Restaurants restaurant;

    /**
     * Restaurant Manager parameter Constructor
     * @param firstName
     * @param lastName
     * @param email
     * @param password
     * @param restaurant
     */
	public Managers(String firstName, String lastName, String email, String password, Restaurants restaurant) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.restaurant = restaurant;
	}

	/**
	 * Default Restaurant Manager Constructor
	 */
	public Managers() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 * @return Restaurant that Manager was assigned to
	 */
	public Restaurants getRestaurant() {
		return restaurant;
	}

	/**
	 * 
	 * @param restaurant Assigns Restaurant to Manager
	 */
	public void setRestaurant(Restaurants restaurant) {
		this.restaurant = restaurant;
	}
    
}
