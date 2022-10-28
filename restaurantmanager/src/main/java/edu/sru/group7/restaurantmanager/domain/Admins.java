package edu.sru.group7.restaurantmanager.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Class that creates 'Admins' entity for Database
 * @author Bradley Smith
 *
 */
@Entity
public class Admins {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private String firstName;
    
    private String lastName;
    
    @Column(unique = true)
    private String email;
    
    private String password;
    
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "admin")
    private List<Restaurants> restaurant = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name = "office_id")
	private Offices office;
    
    
    /**
     * Constructor for Admins 
     * @param firstName
     * @param lastName
     * @param email
     * @param password
     * @param office
     */
	public Admins(String firstName, String lastName, String email, String password,  Offices office) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.office = office;
	}

	/**
	 * Constructor for Admins
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param restaurant
	 * @param office
	 */
	public Admins(long id, String firstName, String lastName, String email, String password,
			List<Restaurants> restaurant, Offices office) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.restaurant = restaurant;
		this.office = office;
	}
	
	/**
	 * Constructor for Admins with no parameters
	 */
	public Admins() {
		super();
	}
	
	/**
	 * Gets Admins id
	 * @return id
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Sets Admins id
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * Returns Admins first name
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Sets Admins first name
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Returns Admins last name
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Sets Admins last name
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Returns Admins email
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Sets Admins email
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Returns Admins password
	 * @return password 
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets Admins password
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Returns the list of 'Restaurants' that 'Admins' are assigned
	 * @return restaurant
	 */
	public List<Restaurants> getRestaurant() {
		return restaurant;
	}

	/**
	 * Sets 'Restaurants' that 'Admins' are assigned
	 * @param restaurant
	 */
	public void setRestaurant(List<Restaurants> restaurant) {
		this.restaurant = restaurant;
	}
	
	/**
	 * Returns the 'Offices' location for 'Admins'
	 * @return office
	 */
	public Offices getOffice() {
		return office;
	}

	/**
	 * Sets the 'Offices' location for 'Admins'
	 * @param office
	 */
	public void setOffice(Offices office) {
		this.office = office;
	}
	

	@Override
	public String toString() {
		return "id: " + id;
	}
	
	
}
