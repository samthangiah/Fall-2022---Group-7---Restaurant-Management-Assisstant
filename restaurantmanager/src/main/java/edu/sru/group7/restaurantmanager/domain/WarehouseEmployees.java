package edu.sru.group7.restaurantmanager.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class WarehouseEmployees {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private String firstName;
    
    private String lastName;
    
    private String email;
    
    private String password;
    
    private float hourlyRate;
    
    private boolean isOnDuty;
    
    private String lastClockedIn;
    
    private float totalHours;
    
    private float weekHours;
    
    @ManyToOne
	@JoinColumn(name="warehouse_id")
	private Warehouses warehouse;

    public WarehouseEmployees(String firstName, String lastName, String email, String password, float hourlyRate, Warehouses warehouse) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.hourlyRate = hourlyRate;
		this.isOnDuty = false;
		this.lastClockedIn = "N/A";
		this.totalHours = 0;
		this.weekHours = 0;
		this.warehouse = warehouse;
	}

	public WarehouseEmployees() {
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

	public float getHourlyRate() {
		return hourlyRate;
	}

	public void setHourlyRate(float hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	public boolean getIsOnDuty() {
		return isOnDuty;
	}

	public void setIsOnDuty(boolean isOnDuty) {
		this.isOnDuty = isOnDuty;
	}
	
	public String getLastClockedIn() {
		return lastClockedIn;
	}

	public void setLastClockedIn(String lastClockedIn) {
		this.lastClockedIn = lastClockedIn;
	}

	public float getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(float totalHours) {
		this.totalHours = totalHours;
	}

	public float getWeekHours() {
		return weekHours;
	}

	public void setWeekHours(float weekHours) {
		this.weekHours = weekHours;
	}
	
	public Warehouses getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouses warehouse) {
		this.warehouse = warehouse;
	}

	@Override
	public String toString() {
		return "id: " + id;
	}
    
}