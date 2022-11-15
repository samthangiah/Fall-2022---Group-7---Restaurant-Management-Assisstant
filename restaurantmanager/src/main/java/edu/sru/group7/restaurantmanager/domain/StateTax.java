package edu.sru.group7.restaurantmanager.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StateTax {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	private long id;
	
	private String state;
	
	private float incomePercent;
	
	private float salesPercent;
	
	public StateTax() {
		
	}
	
	public StateTax(String state, float incomePercent, float salesPercent) {
		this.state = state;
		this.incomePercent = incomePercent;
		this.salesPercent = salesPercent;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
		
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public float getincomePercent() {
		return incomePercent;
	}

	public void setIncomePercent(float incomePercent) {
		this.incomePercent = incomePercent;
	}
	
	public float getSalesPercent() {
		return salesPercent;
	}

	public void setSalesPercent(float salesPercent) {
		this.salesPercent = salesPercent;
	}
}