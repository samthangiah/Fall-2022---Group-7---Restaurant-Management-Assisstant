package edu.sru.group7.restaurantmanager.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test class for WarehouseEmployees class
 */
@SpringBootTest
public class WarehouseEmployeesTest {
	
	@Test
	public void gettersAndSettersTests() {
		WarehouseEmployees whEmployee = new WarehouseEmployees();
		
		whEmployee.setId(1);
		assertEquals((long) 1, whEmployee.getId(), "whEmployee getId should return Id");
		whEmployee.setId(2);
		assertEquals((long) 2, whEmployee.getId(), "whEmployee setId should set Id property");
		
		whEmployee.setFirstName("test");
		assertEquals("test", whEmployee.getFirstName(), "whEmployee getFirstName should return firstName");
		whEmployee.setFirstName("firstName");
		assertEquals("firstName", whEmployee.getFirstName(), "whEmployee setFirstName should set firstName property");
		
		whEmployee.setLastName("test");
		assertEquals("test", whEmployee.getLastName(), "whEmployee getLastName should return lastName");
		whEmployee.setLastName("lastName");
		assertEquals("lastName", whEmployee.getLastName(), "whEmployee setLastName should set lastName property");
		
		whEmployee.setEmail("test");
		assertEquals("test", whEmployee.getEmail(), "whEmployee getEmail should return email");
		whEmployee.setEmail("email@email.com");
		assertEquals("email@email.com", whEmployee.getEmail(), "whEmployee setEmail should set email property");
		
		whEmployee.setPassword("test");
		assertEquals("test", whEmployee.getPassword(), "whEmployee getPassword should return password");
		whEmployee.setPassword("password");
		assertEquals("password", whEmployee.getPassword(), "whEmployee setPassword should set password property");
		
		whEmployee.setHourlyRate(0.00F);
		assertEquals(0.00F, whEmployee.getHourlyRate(), "whEmployee getHourlyRate should return hourlyRate");
		whEmployee.setHourlyRate(10.00F);
		assertEquals(10.00F, whEmployee.getHourlyRate(), "whEmployee setHourlyRate should set hourlyRate property");
		
		whEmployee.setIsOnDuty(false);
		assertFalse(whEmployee.getIsOnDuty(), "whEmployee getIsOnDuty should return isOnDuty");
		whEmployee.setIsOnDuty(true);
		assertTrue(whEmployee.getIsOnDuty(), "whEmployee setIsOnDuty should set isOnDuty property");
		
		whEmployee.setLastClockedIn("N/A");
		assertEquals("N/A", whEmployee.getLastClockedIn(), "whEmployee getLastClockedIn should return lastClockedIn");
		whEmployee.setLastClockedIn("2022-12-04 12:00:00");
		assertEquals("2022-12-04 12:00:00", whEmployee.getLastClockedIn(), "whEmployee setLastClockedIn should set lastClockedIn property");
		
		whEmployee.setWeekHours(0.0F);
		assertEquals(0.0F, whEmployee.getWeekHours(), "whEmployee getWeekHours should return weekHours");
		whEmployee.setWeekHours(40.0F);
		assertEquals(40.0F, whEmployee.getWeekHours(), "whEmployee setWeekHours should set weekHours property");
		
		whEmployee.setTotalHours(0.0F);
		assertEquals(0.0F, whEmployee.getTotalHours(), "whEmployee getTotalHours should return totalHours");
		whEmployee.setTotalHours(40.0F);
		assertEquals(40.0F, whEmployee.getTotalHours(), "whEmployee setTotalHours should set totalHours property");
		
		Warehouses warehouse = new Warehouses();
		
		whEmployee.setWarehouse(warehouse);
		assertEquals(warehouse, whEmployee.getWarehouse(), "whEmployee getWarehouse should return warehouse");
		whEmployee.setWarehouse(null);
		assertNull(whEmployee.getWarehouse(), "whEmployee setWarehouse should set warehouse property");
		
		assertEquals("id: 2", whEmployee.toString(), "whEmployee toString should return string id");
	}
}
