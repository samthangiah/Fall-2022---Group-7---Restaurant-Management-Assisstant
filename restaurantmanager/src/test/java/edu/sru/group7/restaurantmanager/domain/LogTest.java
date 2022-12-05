package edu.sru.group7.restaurantmanager.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test class for Log class
 */
@SpringBootTest
public class LogTest {
	
	@Test
	public void gettersAndSettersTests() {
		Log log = new Log();
		
		log.setId(1);
		assertEquals((long) 1, log.getId(), "log getId should return Id");
		log.setId(2);
		assertEquals((long) 2, log.getId(), "log setId should set Id property");
		
		log.setLocation("test");
		assertEquals("test", log.getLocation(), "log getLocation should return location");
		log.setLocation("restaurant address");
		assertEquals("restaurant address", log.getLocation(), "log setLocation should set location property");
		
		log.setDate("test");
		assertEquals("test", log.getDate(), "log getDate should return date");
		log.setDate("2022-12-04");
		assertEquals("2022-12-04", log.getDate(), "log setDate should set date property");
		
		log.setTime("test");
		assertEquals("test", log.getTime(), "log getTime should return time");
		log.setTime("12:00:00");
		assertEquals("12:00:00", log.getTime(), "log setTime should set time property");
		
		log.setUserId((long) 1);
		assertEquals((long) 1, log.getUserId(), "log getUserId should return userId");
		log.setUserId((long) 2);
		assertEquals((long) 2, log.getUserId(), "log setUserId should set userId property");
		
		log.setAction("test");
		assertEquals("test", log.getAction(), "log getAction should return action");
		log.setAction("action");
		assertEquals("action", log.getAction(), "log setAction should set action property");
		
		log.setActionId((long) 1);
		assertEquals((long) 1, log.getActionId(), "log getActionId should return actionId");
		log.setActionId((long) 2);
		assertEquals((long) 2, log.getActionId(), "log setActionId should set actionId property");
	}
}
