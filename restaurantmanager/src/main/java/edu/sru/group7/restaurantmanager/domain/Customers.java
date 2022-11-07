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
    
    private int orderHistory;
    
    private boolean rewardsMember;
    
    private int rewardsAvailable;
    
    private int location;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "customer_id")
	private List<Orders> order = new ArrayList<>();
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "customer_id")
	private Set<CartItems> cartItems = new HashSet<>();
    
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
	public Customers(String firstName, String lastName, String email, String password, int orderHistory,
			boolean rewardsMember, int rewardsAvailable, int location) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.orderHistory = orderHistory;
		this.rewardsMember = rewardsMember;
		this.rewardsAvailable = rewardsAvailable;
		this.location = location;
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
	
	public int getOrderHistory() {
		return orderHistory;
	}

	public void setOrderHistory(int orderHistory) {
		this.orderHistory = orderHistory;
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
	
	public int getLocation() {
		return location;
	}
	
	public void setLocation(int location) {
		this.location = location;
	}
	
	/**
	 * @return List of Orders belonging to that Customer
	 */
	public List<Orders> getOrder() {
		return order;
	}

	/**
	 * @param order Assigns list of Orders to that Customer
	 */
	public void setOrder(List<Orders> order) {
		this.order = order;
	}

	@Override
	/**
	 * To String Override
	 */
	public String toString() {
		return "First name=" + firstName + ", Last name=" + lastName + ", Email=" + email;
	}
	
}