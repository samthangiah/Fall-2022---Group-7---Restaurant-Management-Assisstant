package edu.sru.group7.restaurantmanager.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.sru.group7.restaurantmanager.domain.Admins;
import edu.sru.group7.restaurantmanager.domain.Warehouses;

/**
 * Test class for Warehouse repository class
 */
@SpringBootTest
public class WarehouseRepositoryTest {
	
	@Autowired
	WarehouseRepository warehouseRepo;
	
	@Autowired
	AdminRepository adminRepo;
	
	@Test
	public void findByAdminTest() {
		adminRepo.deleteAll();
		warehouseRepo.deleteAll();
		Admins admin = new Admins();
		admin.setEmail("email@email.com");
		admin = adminRepo.save(admin);
		Warehouses wh = new Warehouses();
		wh.setAdmin(admin);
		warehouseRepo.save(wh);
		
		List<Warehouses> warehouses = warehouseRepo.findByAdmin(admin.getId());
		assertNotNull(warehouses, "warehouseRepo should not be null");
		assertFalse(warehouses.isEmpty(), "warehouseRepo should not be empty");
	}
}