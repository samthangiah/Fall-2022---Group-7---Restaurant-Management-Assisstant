package edu.sru.group7.restaurantmanager.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test class for Shipping class
 */
@SpringBootTest
public class ShippingTest {
	
	@Test
	public void gettersAndSettersTests() {
		Shipping shipping = new Shipping();
		
		shipping.setId(1);
		assertEquals((long) 1, shipping.getId(), "shipping getId should return Id");
		shipping.setId(2);
		assertEquals((long) 2, shipping.getId(), "shipping setId should set Id property");
		
		Warehouses warehouse = new Warehouses();
		Restaurants restaurant = new Restaurants();
		
		shipping.setWarehouse_id(warehouse);
		assertEquals(warehouse, shipping.getWarehouse_id(), "shipping getWarehouse_id should return warehouses");
		shipping.setWarehouse_id(null);
		assertNull(shipping.getWarehouse_id(), "shipping setWarehouse_id should set warehouse property");
		
		shipping.setRestaurant_id(restaurant);
		assertEquals(restaurant, shipping.getRestaurant_id(), "shipping getRestaurant_id should return restaurant");
		shipping.setRestaurant_id(null);
		assertNull(shipping.getRestaurant_id(), "shipping setRestaurant_id should set restaurant property");
		
		shipping.setStatus("test");
		assertEquals("test", shipping.getStatus(), "shipping getStatus should return status");
		shipping.setStatus("pending");
		assertEquals("pending", shipping.getStatus(), "shipping setStatus should set status property");
		
		Managers manager = new Managers();
		WarehouseManager whmanager = new WarehouseManager();
		
		shipping.setManager_id(manager);
		assertEquals(manager, shipping.getManager_id(), "shipping getManager_id should return manager");
		shipping.setManager_id(null);
		assertNull(shipping.getManager_id(), "shipping setManager_id should set manager property");
		
		shipping.setWarehousemanager_id(whmanager);
		assertEquals(whmanager, shipping.getWarehousemanager_id(), "shipping getWarehousemanager_id should return warehousemanager");
		shipping.setWarehousemanager_id(null);
		assertNull(shipping.getWarehousemanager_id(), "shipping setWarehousemanager_id should set warehousemanager property");
		
		shipping.setIngredient("test");
		assertEquals("test", shipping.getIngredient(), "shipping getIngredient should return ingredient");
		shipping.setIngredient("ingredient");
		assertEquals("ingredient", shipping.getIngredient(), "shipping setIngredient should set ingredient property");
		
		shipping.setQuantity(1);
		assertEquals(1, shipping.getQuantity(), "shipping getQuantity should return quantity");
		shipping.setQuantity(2);
		assertEquals(2, shipping.getQuantity(), "shipping setQuantity should set quantity property");
		
		shipping.setExplanation("test");
		assertEquals("test", shipping.getExplanation(), "shipping getExplanation should return explanation");
		shipping.setExplanation("explanation");
		assertEquals("explanation", shipping.getExplanation(), "shipping setExplanation should set explanation property");
		
		shipping.setDate("test");
		assertEquals("test", shipping.getDate(), "shipping getDate should return date");
		shipping.setDate("2022-12-04");
		assertEquals("2022-12-04", shipping.getDate(), "shipping setDate should set date property");
		
		shipping.setTime("test");
		assertEquals("test", shipping.getTime(), "shipping getTime should return time");
		shipping.setTime("12:00:00");
		assertEquals("12:00:00", shipping.getTime(), "shipping setTime should set time property");
	}
}
