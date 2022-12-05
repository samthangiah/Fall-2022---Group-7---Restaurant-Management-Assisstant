package edu.sru.group7.restaurantmanager.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.sru.group7.restaurantmanager.domain.Managers;
import edu.sru.group7.restaurantmanager.domain.Restaurants;

/**
 * Test class for Manager repository class
 */
@SpringBootTest
public class ManagerRepositoryTest {
	
	@Autowired
	ManagerRepository managerRepo;
	
	@Autowired
	RestaurantRepository restaurantRepo;
	
	@Test
	public void findByRestaurantTest() {
		Restaurants rest = new Restaurants();
		Managers manager = new Managers();
		List<Managers> m = new ArrayList<Managers>();
		m.add(manager);
		rest.setManagers(m);
		manager.setRestaurant(rest);
		rest = restaurantRepo.save(rest);
		managerRepo.save(manager);
		
		List<Managers> managers = managerRepo.findByRestaurant(rest.getId());
		assertNotNull(managers, "managerRepo should not be null");
		assertFalse(managers.isEmpty(), "manager list should not be empty");
	}
}