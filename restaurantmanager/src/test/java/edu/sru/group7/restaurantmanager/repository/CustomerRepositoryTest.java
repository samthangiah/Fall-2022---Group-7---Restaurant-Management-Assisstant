package edu.sru.group7.restaurantmanager.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.sru.group7.restaurantmanager.domain.Customers;

/**
 * Test class for Customer repository class
 */
@SpringBootTest
public class CustomerRepositoryTest {
	
	@Autowired
	CustomerRepository customerRepo;

	@Test
	public void findByEmailTest() {
		Customers cust = new Customers();
		cust.setEmail("email@email.com");
		customerRepo.save(cust);
		
		assertNotNull(customerRepo.findByEmail("email@email.com"), "customer should not be null");
	}
}