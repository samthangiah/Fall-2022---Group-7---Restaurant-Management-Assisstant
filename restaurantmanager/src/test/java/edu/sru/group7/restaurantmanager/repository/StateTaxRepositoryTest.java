package edu.sru.group7.restaurantmanager.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.sru.group7.restaurantmanager.domain.StateTax;

/**
 * Test class for StateTax repository class
 */
@SpringBootTest
public class StateTaxRepositoryTest {
	
	@Autowired
	StateTaxRepository stateTaxRepo;
	
	@Test
	public void findByStateTest() {
		StateTax st = new StateTax();
		st.setState("PA");
		stateTaxRepo.save(st);
		
		assertNotNull(stateTaxRepo.findByState("PA"), "stateTaxRepo should find tax by state");
	}
}