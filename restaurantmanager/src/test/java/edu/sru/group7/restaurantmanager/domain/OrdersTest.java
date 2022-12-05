package edu.sru.group7.restaurantmanager.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test class for Orders class
 */
@SpringBootTest
public class OrdersTest {
	
	@Test
	public void gettersAndSettersTests() {
		Orders order = new Orders();
		
		order.setId(1);
		assertEquals((long) 1, order.getId(), "order getId should return Id");
		order.setId(2);
		assertEquals((long) 2, order.getId(), "order setId should set Id property");
		
		order.setDate("test");
		assertEquals("test", order.getDate(), "order getDate should return date");
		order.setDate("2022-12-04");
		assertEquals("2022-12-04", order.getDate(), "order setDate should set date property");
		
		order.setPrice(0.00F);
		assertEquals(0.00F, order.getPrice(), "order getPrice should return price");
		order.setPrice(1.99F);
		assertEquals(1.99F, order.getPrice(), "order setPrice should set price property");
		
		order.setInstructions("test");
		assertEquals("test", order.getInstructions(), "order getInstructions should return instructions");
		order.setInstructions("hold the sauce");
		assertEquals("hold the sauce", order.getInstructions(), "order setInstructions should set instructions property");
		
		order.setStatus("test");
		assertEquals("test", order.getStatus(), "order getStatus should return status");
		order.setStatus("pending");
		assertEquals("pending", order.getStatus(), "order setStatus should set status property");
		
		Restaurants restaurant = new Restaurants();
		Menu menu = new Menu();
		Set<Menu> items = new HashSet<Menu>();
		items.add(menu);
		Customers customer = new Customers();
		
		order.setRestaurant(restaurant);
		assertEquals(restaurant, order.getRestaurant(), "order getRestaurant should return restaurant");
		order.setRestaurant(null);
		assertNull(order.getRestaurant(), "order setRestaurant should set restaurant property");
		
		order.setItems(items);
		assertEquals(items, order.getItems(), "order getItems should return items");
		order.setItems(null);
		assertNull(order.getItems(), "order setItems should set items property");
		
		order.setCustomer_id(customer);
		assertEquals(customer, order.getCustomer_id(), "order getCustomer_id should return customer");
		order.setCustomer_id(null);
		assertNull(order.getCustomer_id(), "order setCustomer_id should set customer property");
	}
	
	@Test
	public void RemoveMenuItemTest() {
		//Setup
		Orders order = new Orders();
		Menu menu = new Menu();
		Set<Menu> items = new HashSet<Menu>();
		items.add(menu);
		order.setItems(items);
		
		order.removeMenuItem(menu);
		assertTrue(order.getItems().isEmpty(), "order items should have removed menu item");
	}
}
