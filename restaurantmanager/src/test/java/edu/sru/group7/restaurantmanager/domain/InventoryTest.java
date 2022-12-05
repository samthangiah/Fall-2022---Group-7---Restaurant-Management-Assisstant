package edu.sru.group7.restaurantmanager.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test class for Inventory class
 */
@SpringBootTest
public class InventoryTest {
	
	@Test
	public void gettersAndSettersTests() {
		Inventory inventory = new Inventory();
		
		inventory.setId(1);
		assertEquals((long) 1, inventory.getId(), "inventory getId should return Id");
		inventory.setId(2);
		assertEquals((long) 2, inventory.getId(), "inventory setId should set Id property");
		
		inventory.setIngredient("test");
		assertEquals("test", inventory.getIngredient(), "inventory getIngredient should return ingredients");
		inventory.setIngredient("ingredient");
		assertEquals("ingredient", inventory.getIngredient(), "inventory setIngredient should set ingredient property");
		
		inventory.setQuantity(1);
		assertEquals(1, inventory.getQuantity(), "inventory getQuantity should return quantity");
		inventory.setQuantity(2);
		assertEquals(2, inventory.getQuantity(), "inventory setQuantity should set quantity property");
		
		Restaurants restaurant = new Restaurants();
		Warehouses warehouse = new Warehouses();
		
		inventory.setRestaurant_id(restaurant);
		assertEquals(restaurant, inventory.getRestaurant_id(), "inventory getRestaurant_id should return restaurants");
		inventory.setRestaurant_id(null);
		assertNull(inventory.getRestaurant_id(), "inventory setRestaurant_id should set restaurant property");
		
		inventory.setWarehouse_id(warehouse);
		assertEquals(warehouse, inventory.getWarehouse_id(), "inventory getWarehouse_id should return warehouses");
		inventory.setWarehouse_id(null);
		assertNull(inventory.getWarehouse_id(), "inventory setWarehouse_id should set warehouse property");
	}
}
