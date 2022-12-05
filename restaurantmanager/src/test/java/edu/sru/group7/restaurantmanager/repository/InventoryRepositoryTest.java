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
import edu.sru.group7.restaurantmanager.domain.Inventory;
import edu.sru.group7.restaurantmanager.domain.Restaurants;
import edu.sru.group7.restaurantmanager.domain.Warehouses;

/**
 * Test class for Inventory repository class
 */
@SpringBootTest
public class InventoryRepositoryTest {
	
	@Autowired
	InventoryRepository inventoryRepo;
	
	@Autowired
	RestaurantRepository restaurantRepo;
	
	@Autowired
	WarehouseRepository warehouseRepo;
	
	@Autowired
	AdminRepository adminRepo;
	
	@BeforeEach
	public void setup() {
		adminRepo.deleteAll();
		inventoryRepo.deleteAll();
		restaurantRepo.deleteAll();
		warehouseRepo.deleteAll();
		
		Inventory inventory = new Inventory();
		Admins admin = new Admins();
		admin.setEmail("email@email.com");
		Restaurants rest = new Restaurants();
		Warehouses wh = new Warehouses();
		inventory.setRestaurant_id(rest);
		inventory.setWarehouse_id(wh);
		List<Restaurants> r = new ArrayList<Restaurants>();
		r.add(rest);
		List<Warehouses> w = new ArrayList<Warehouses>();
		w.add(wh);
		admin.setRestaurant(r);
		admin.setWarehouse(w);
		rest.setAdmin(admin);
		wh.setAdmin(admin);
		
		adminRepo.save(admin);
		restaurantRepo.save(rest);
		warehouseRepo.save(wh);
		inventoryRepo.save(inventory);
	}
	
	@Test
	public void findInventoryRestaurantTest() {
		Admins admin = adminRepo.findAdminByEmail("email@email.com");
		List<Restaurants> rests = restaurantRepo.findByAdmin(admin.getId());
		List<Inventory> inventory = inventoryRepo.findInventoryRestaurant(rests.get(0).getId());
		assertNotNull(inventory, "inventory should not be null");
		assertFalse(inventory.isEmpty(), "inventory should not be empty");
	}
	
	@Test
	public void findInventoryWarehouseTest() {
		Admins admin = adminRepo.findAdminByEmail("email@email.com");
		List<Warehouses> warehouses = warehouseRepo.findByAdmin(admin.getId());
		List<Inventory> inventory = inventoryRepo.findInventoryWarehouse(warehouses.get(0).getId());
		assertNotNull(inventory, "inventory should not be null");
		assertFalse(inventory.isEmpty(), "inventory should not be empty");
	}
}