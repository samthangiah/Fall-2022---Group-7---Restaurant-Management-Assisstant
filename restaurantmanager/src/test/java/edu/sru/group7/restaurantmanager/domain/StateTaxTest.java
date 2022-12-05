package edu.sru.group7.restaurantmanager.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test class for StateTax class
 */
@SpringBootTest
public class StateTaxTest {
	
	@Test
	public void gettersAndSettersTests() {
		StateTax stateTax = new StateTax();
		
		stateTax.setId(1);
		assertEquals((long) 1, stateTax.getId(), "stateTax getId should return Id");
		stateTax.setId(2);
		assertEquals((long) 2, stateTax.getId(), "stateTax setId should set Id property");
		
		stateTax.setState("test");
		assertEquals("test", stateTax.getState(), "stateTax getState should return state");
		stateTax.setState("PA");
		assertEquals("PA", stateTax.getState(), "stateTax setState should set state property");
		
		stateTax.setIncomePercent(0.0F);
		assertEquals(0.0F, stateTax.getincomePercent(), "stateTax getincomePercent should return incomePercent");
		stateTax.setIncomePercent(5.0F);
		assertEquals(5.0F, stateTax.getincomePercent(), "stateTax setincomePercent should set incomePercent property");
		
		stateTax.setSalesPercent(0.0F);
		assertEquals(0.0F, stateTax.getSalesPercent(), "stateTax getSalesPercent should return salesPercent");
		stateTax.setSalesPercent(5.0F);
		assertEquals(5.0F, stateTax.getSalesPercent(), "stateTax setSalesPercent should set salesPercent property");
	}
}
