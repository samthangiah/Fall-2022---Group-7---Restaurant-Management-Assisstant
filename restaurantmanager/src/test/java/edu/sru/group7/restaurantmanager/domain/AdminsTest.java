package edu.sru.group7.restaurantmanager.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test class for Admins class
 */
@SpringBootTest
public class AdminsTest {
	
	@Test
	public void gettersAndSettersTests() {
		Admins admin = new Admins();
		
		admin.setId(1);
		assertEquals((long) 1, admin.getId(), "Admin getId should return Id");
		admin.setId(2);
		assertEquals((long) 2, admin.getId(), "Admin setId should set Id property");
		
		admin.setFirstName("test");
		assertEquals("test", admin.getFirstName(), "Admin getFirstName should return firstName");
		admin.setFirstName("firstName");
		assertEquals("firstName", admin.getFirstName(), "Admin setFirstName should set firstName property");
		
		admin.setLastName("test");
		assertEquals("test", admin.getLastName(), "Admin getLastName should return lastName");
		admin.setLastName("lastName");
		assertEquals("lastName", admin.getLastName(), "Admin setLastName should set lastName property");
		
		admin.setEmail("test");
		assertEquals("test", admin.getEmail(), "Admin getEmail should return email");
		admin.setEmail("email@email.com");
		assertEquals("email@email.com", admin.getEmail(), "Admin setEmail should set email property");
		
		admin.setPassword("test");
		assertEquals("test", admin.getPassword(), "Admin getPassword should return password");
		admin.setPassword("password");
		assertEquals("password", admin.getPassword(), "Admin setPassword should set password property");
		
		Restaurants restaurant = new Restaurants();
		List<Restaurants> restaurants = new ArrayList<Restaurants>();
		restaurants.add(restaurant);
		Offices office = new Offices();
		Warehouses warehouse = new Warehouses();
		List<Warehouses> warehouses = new ArrayList<Warehouses>();
		warehouses.add(warehouse);
		
		admin.setRestaurant(restaurants);
		assertEquals(restaurants, admin.getRestaurant(), "Admin getRestaurant should return restaurants");
		admin.setRestaurant(null);
		assertNull(admin.getRestaurant(), "Admin setRestaurant should set restaurant property");
		
		admin.setOffice(office);
		assertEquals(office, admin.getOffice(), "Admin getOffice should return office");
		admin.setOffice(null);
		assertNull(admin.getOffice(), "Admin setOffice should set office property");
		
		admin.setWarehouse(warehouses);
		assertEquals(warehouses, admin.getWarehouse(), "Admin getWarehouse should return warehouses");
		admin.setWarehouse(null);
		assertNull(admin.getWarehouse(), "Admin setWarehouse should set warehouse property");
		
		assertEquals("id: 2", admin.toString(), "Admin toString should return string id");
	}
}
