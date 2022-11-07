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
     * Admins Constructor without any Restaurants assigned
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
	 * Admins Constructor with all parameters
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
	 * Default Admins Constructor
	 */
	public Admins() {
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
	 * @return restaurant Gets List of Restaurants Admin is assigned
	 */
	public List<Restaurants> getRestaurant() {
		return restaurant;
	}

	/**
	 * @param restaurant Sets List of Restaurants Admin is assigned
	 */
	public void setRestaurant(List<Restaurants> restaurant) {
		this.restaurant = restaurant;
	}
	
	/**
	 * @return office, Office location of Admin
	 */
	public Offices getOffice() {
		return office;
	}

	/**
	 * @param office Assigns Admin to an Office location
	 */
	public void setOffice(Offices office) {
		this.office = office;
	}
	

	@Override
	public String toString() {
		return "id: " + id;
	}
	
	
}
