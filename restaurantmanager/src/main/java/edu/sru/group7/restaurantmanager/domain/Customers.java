package edu.sru.group7.restaurantmanager.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
/**
 * 
 * 'Customers' Entity that holds all users of the program
 *
 */
public class Customers {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private String firstName;
    
    private String lastName;
    
    private String email;
    
    private String password;

    private boolean rewardsMember;
    
    private int rewardsAvailable;
    
    private String location;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "customer_id")
	private List<Orders> orderHistory = new ArrayList<Orders>();
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "customer_id")
	private Set<CartItems> cartItems = new HashSet<CartItems>();
    
    /**
     * Default Constructor
     */
    public Customers() {}
    
    /**
     * Constructor with parameters
     * @param firstName
     * @param lastName
     * @param email
     * @param password
     * @param orderHistory
     * @param rewardsMember
     * @param rewardsAvailable
     * @param location
     */
	public Customers(String firstName, String lastName, String email, String password,
			boolean rewardsMember, int rewardsAvailable, String location) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.rewardsMember = rewardsMember;
		this.rewardsAvailable = rewardsAvailable;
		this.location = location;
		this.orderHistory = new ArrayList<Orders>();
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
	
	public boolean getRewardsMember() {
		return rewardsMember;
	}
	
	public void setRewardsMember(boolean rewardsMember) {
		this.rewardsMember = rewardsMember;
	}
	
	public int getRewardsAvailable() {
		return rewardsAvailable;
	}
	
	public void setRewardsAvailable(int rewardsAvailable) {
		this.rewardsAvailable = rewardsAvailable;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	/**
	 * @return List of Orders belonging to that Customer
	 */
	public List<Orders> getOrderHistory() {
		return orderHistory;
	}

	/**
	 * @param order Assigns list of Orders to that Customer
	 */
	public void setOrderHistory(List<Orders> order) {
		this.orderHistory = order;
	}

	public Set<CartItems> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItems> cartItems) {
		this.cartItems = cartItems;
	}

	@Override
	/**
	 * To String Override
	 */
	public String toString() {
		return "Name: " + firstName + " " + lastName + ", Email: " + email;
	}
	
}