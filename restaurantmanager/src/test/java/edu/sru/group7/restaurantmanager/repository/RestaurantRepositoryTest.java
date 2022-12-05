package edu.sru.group7.restaurantmanager.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.sru.group7.restaurantmanager.domain.Admins;
import edu.sru.group7.restaurantmanager.domain.Restaurants;
import edu.sru.group7.restaurantmanager.domain.Warehouses;

/**
 * Test class for Restaurant repository class
 */
@SpringBootTest
public class RestaurantRepositoryTest {
	
	@Autowired
	RestaurantRepository restaurantRepo;
	
	@Autowired
	AdminRepository adminRepo;
	
	@BeforeEach
	public void setup() {
		adminRepo.deleteAll();
		restaurantRepo.deleteAll();
		
		Admins admin = new Admins();
		admin.setEmail("email@email.com");
		Restaurants rest = new Restaurants();
		List<Restaurants> r = new ArrayList<Restaurants>();
		r.add(rest);
		List<Warehouses> w = new ArrayList<Warehouses>();
		admin.setRestaurant(r);
		admin.setWarehouse(w);
		rest.setAdmin(admin);
		Restaurants rest2 = new Restaurants();
		
		restaurantRepo.save(rest2);
		adminRepo.save(admin);
		restaurantRepo.save(rest);
	}
	
	@Test
	public void findByAdminTest() {
		Admins admin = adminRepo.findAdminByEmail("email@email.com");
		List<Restaurants> rests = restaurantRepo.findByAdmin(admin.getId());
		assertNotNull(rests, "restaurantRepo should not return null");
		assertFalse(rests.isEmpty(), "restaurantRepo should not be empty");
	}
	
	@Test
	public void findMissingAdminTest() {
		List<Restaurants> rests = restaurantRepo.findMissingAdmin();
		assertNotNull(rests, "restaurantRepo should not return null");
		assertFalse(rests.isEmpty(), "restaurantRepo should not be empty");
	}
}