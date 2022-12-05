package edu.sru.group7.restaurantmanager.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test class for Shipment class
 */
@SpringBootTest
public class ShipmentTest {
	
	@Test
	public void gettersAndSettersTests() {
		Shipment shipment = new Shipment();
		Shipping shipping = new Shipping();
		List<Shipping> shipments = new ArrayList<Shipping>();
		shipments.add(shipping);
		
		shipment.setShipping(shipments);
		assertEquals(shipments, shipment.getShipping(), "shipment getShipping should return shipments");
		shipment.setShipping(null);
		assertNull(shipment.getShipping(), "shipment setShipping should set shipments property");
	}
	
	@Test
	public void addShippingTest() {
		Shipment shipment = new Shipment();
		Shipping shipping = new Shipping();
		
		shipment.addShipping(shipping);
		assertTrue(shipment.getShipping().contains(shipping), "shipment addShipping should add shipping");
	}
}
