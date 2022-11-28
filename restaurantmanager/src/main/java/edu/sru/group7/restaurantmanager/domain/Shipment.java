package edu.sru.group7.restaurantmanager.domain;

import java.util.ArrayList;
import java.util.List;

public class Shipment {

	private List<Shipping> shipping = new ArrayList<>();
	
	public void addShipping(Shipping shipment) {
		this.shipping.add(shipment);
	}
	
	public Shipment() {
		super();
	}
	
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
