package edu.sru.group7.restaurantmanager.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.sru.group7.restaurantmanager.domain.Customers;
import edu.sru.group7.restaurantmanager.domain.Log;

/**
 * Test class for Log repository class
 */
@SpringBootTest
public class LogRepositoryTest {
	
	@Autowired
	LogRepository logRepo;
	
	@Autowired
	CustomerRepository customerRepo;
	
	@Test
	public void findByIdTest() {
		Customers cust = new Customers();
		cust = customerRepo.save(cust);
		Log log = new Log();
		log.setUserId(cust.getId());
		logRepo.save(log);
		
		assertNotNull(logRepo.findByID(cust), "logRepo should not be null");
	}
}