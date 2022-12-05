package edu.sru.group7.restaurantmanager.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test class for Servers class
 */
@SpringBootTest
public class ServersTest {
	
	@Test
	public void gettersAndSettersTests() {
		Servers server = new Servers();
		
		server.setId(1);
		assertEquals((long) 1, server.getId(), "server getId should return Id");
		server.setId(2);
		assertEquals((long) 2, server.getId(), "server setId should set Id property");
		
		server.setFirstName("test");
		assertEquals("test", server.getFirstName(), "server getFirstName should return firstName");
		server.setFirstName("firstName");
		assertEquals("firstName", server.getFirstName(), "server setFirstName should set firstName property");
		
		server.setLastName("test");
		assertEquals("test", server.getLastName(), "server getLastName should return lastName");
		server.setLastName("lastName");
		assertEquals("lastName", server.getLastName(), "server setLastName should set lastName property");
		
		server.setEmail("test");
		assertEquals("test", server.getEmail(), "server getEmail should return email");
		server.setEmail("email@email.com");
		assertEquals("email@email.com", server.getEmail(), "server setEmail should set email property");
		
		server.setPassword("test");
		assertEquals("test", server.getPassword(), "server getPassword should return password");
		server.setPassword("password");
		assertEquals("password", server.getPassword(), "server setPassword should set password property");
		
		server.setHourlyRate(0.00F);
		assertEquals(0.00F, server.getHourlyRate(), "server getHourlyRate should return hourlyRate");
		server.setHourlyRate(10.00F);
		assertEquals(10.00F, server.getHourlyRate(), "server setHourlyRate should set hourlyRate property");
		
		server.setIsOnDuty(false);
		assertFalse(server.getIsOnDuty(), "server getIsOnDuty should return isOnDuty");
		server.setIsOnDuty(true);
		assertTrue(server.getIsOnDuty(), "server setIsOnDuty should set isOnDuty property");
		
		server.setLastClockedIn("N/A");
		assertEquals("N/A", server.getLastClockedIn(), "server getLastClockedIn should return lastClockedIn");
		server.setLastClockedIn("2022-12-04 12:00:00");
		assertEquals("2022-12-04 12:00:00", server.getLastClockedIn(), "server setLastClockedIn should set lastClockedIn property");
		
		server.setWeekHours(0.0F);
		assertEquals(0.0F, server.getWeekHours(), "server getWeekHours should return weekHours");
		server.setWeekHours(40.0F);
		assertEquals(40.0F, server.getWeekHours(), "server setWeekHours should set weekHours property");
		
		server.setTotalHours(0.0F);
		assertEquals(0.0F, server.getTotalHours(), "server getTotalHours should return totalHours");
		server.setTotalHours(40.0F);
		assertEquals(40.0F, server.getTotalHours(), "server setTotalHours should set totalHours property");
		
		Restaurants restaurant = new Restaurants();
		
		server.setRestaurant(restaurant);
		assertEquals(restaurant, server.getRestaurant(), "server getRestaurant should return restaurant");
		server.setRestaurant(null);
		assertNull(server.getRestaurant(), "server setRestaurant should set restaurant property");
		
		assertEquals("id: 2", server.toString(), "server toString should return string id");
	}
}
