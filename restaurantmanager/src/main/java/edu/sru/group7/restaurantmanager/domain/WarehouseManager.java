package edu.sru.group7.restaurantmanager.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class WarehouseManager {

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
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "warehouse_id")
	private List<Shipping> shipments = new ArrayList<>();

	public WarehouseManager(long id, String firstName, String lastName, String email, String password, float hourlyRate,
			boolean isOnDuty, String lastClockedIn, float totalHours, float weekHours, Warehouses warehouse,
			List<Shipping> shipments) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.hourlyRate = hourlyRate;
		this.isOnDuty = isOnDuty;
		this.lastClockedIn = lastClockedIn;
		this.totalHours = totalHours;
		this.weekHours = weekHours;
		this.warehouse = warehouse;
		this.shipments = shipments;
	}

	public WarehouseManager() {
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

	public Warehouses getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouses warehouse) {
		this.warehouse = warehouse;
	}

	public List<Shipping> getShipments() {
		return shipments;
	}

	public void setShipments(List<Shipping> shipments) {
		this.shipments = shipments;
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
}


