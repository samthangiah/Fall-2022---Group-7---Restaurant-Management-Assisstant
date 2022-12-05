package edu.sru.group7.restaurantmanager.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test class for Managers class
 */
@SpringBootTest
public class ManagersTest {
	
	@Test
	public void gettersAndSettersTests() {
		Managers manager = new Managers();
		
		manager.setId(1);
		assertEquals((long) 1, manager.getId(), "manager getId should return Id");
		manager.setId(2);
		assertEquals((long) 2, manager.getId(), "manager setId should set Id property");
		
		manager.setFirstName("test");
		assertEquals("test", manager.getFirstName(), "manager getFirstName should return firstName");
		manager.setFirstName("firstName");
		assertEquals("firstName", manager.getFirstName(), "manager setFirstName should set firstName property");
		
		manager.setLastName("test");
		assertEquals("test", manager.getLastName(), "manager getLastName should return lastName");
		manager.setLastName("lastName");
		assertEquals("lastName", manager.getLastName(), "manager setLastName should set lastName property");
		
		manager.setEmail("test");
		assertEquals("test", manager.getEmail(), "manager getEmail should return email");
		manager.setEmail("email@email.com");
		assertEquals("email@email.com", manager.getEmail(), "manager setEmail should set email property");
		
		manager.setPassword("test");
		assertEquals("test", manager.getPassword(), "manager getPassword should return password");
		manager.setPassword("password");
		assertEquals("password", manager.getPassword(), "manager setPassword should set password property");
		
		manager.setHourlyRate(0.00F);
		assertEquals(0.00F, manager.getHourlyRate(), "manager getHourlyRate should return hourlyRate");
		manager.setHourlyRate(10.00F);
		assertEquals(10.00F, manager.getHourlyRate(), "manager setHourlyRate should set hourlyRate property");
		
		manager.setIsOnDuty(false);
		assertFalse(manager.getIsOnDuty(), "manager getIsOnDuty should return isOnDuty");
		manager.setIsOnDuty(true);
		assertTrue(manager.getIsOnDuty(), "manager setIsOnDuty should set isOnDuty property");
		
		manager.setLastClockedIn("N/A");
		assertEquals("N/A", manager.getLastClockedIn(), "manager getLastClockedIn should return lastClockedIn");
		manager.setLastClockedIn("2022-12-04 12:00:00");
		assertEquals("2022-12-04 12:00:00", manager.getLastClockedIn(), "manager setLastClockedIn should set lastClockedIn property");
		
		manager.setWeekHours(0.0F);
		assertEquals(0.0F, manager.getWeekHours(), "manager getWeekHours should return weekHours");
		manager.setWeekHours(40.0F);
		assertEquals(40.0F, manager.getWeekHours(), "manager setWeekHours should set weekHours property");
		
		manager.setTotalHours(0.0F);
		assertEquals(0.0F, manager.getTotalHours(), "manager getTotalHours should return totalHours");
		manager.setTotalHours(40.0F);
		assertEquals(40.0F, manager.getTotalHours(), "manager setTotalHours should set totalHours property");
		
		Restaurants restaurant = new Restaurants();
		Shipping shipment = new Shipping();
		List<Shipping> shipments = new ArrayList<Shipping>();
		shipments.add(shipment);
		
		manager.setRestaurant(restaurant);
		assertEquals(restaurant, manager.getRestaurant(), "manager getRestaurant should return restaurant");
		manager.setRestaurant(null);
		assertNull(manager.getRestaurant(), "manager setRestaurant should set restaurant property");
				
		manager.setShipments(shipments);
		assertEquals(shipments, manager.getShipments(), "manager getShipments should return shipments");
		manager.setShipments(null);
		assertNull(manager.getShipments(), "manager setShipments should set shipments property");
	}
}
