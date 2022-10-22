package edu.sru.group7.restaurantmanager.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.lang.NonNull;

@Entity
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
    
    public Customers() {}

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
	
}