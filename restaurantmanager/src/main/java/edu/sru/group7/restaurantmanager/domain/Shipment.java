package edu.sru.group7.restaurantmanager.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Shipment POJO. Only acts as a method of saving multiple instances of Shipping POJO.
 */
public class Shipment {

	private List<Shipping> shipping = new ArrayList<>();
	
	/**
	 * Method to add Shipping POJO to list of Shipping in Shipment POJO.
	 * @param shipment
	 */
	public void addShipping(Shipping shipment) {
		this.shipping.add(shipment);
	}
	
	/**
	 * Default Constructor
	 */
	public Shipment() {
		super();
	}
	
	/**
	 * Parameterized Constructor
	 * @param shipping
	 */
	public Shipment(List<Shipping> shipping) {
		super();
		this.shipping = shipping;
	}

	public List<Shipping> getShipping() {
		return shipping;
	}

	public void setShipping(List<Shipping> shipping) {
		this.shipping = shipping;
	}

	
	
	
	
}
