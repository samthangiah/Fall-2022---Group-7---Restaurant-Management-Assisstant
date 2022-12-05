package edu.sru.group7.restaurantmanager.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.sru.group7.restaurantmanager.domain.WarehouseManager;
import edu.sru.group7.restaurantmanager.domain.Warehouses;

/**
 * Test class for WarehouseManager repository class
 */
@SpringBootTest
public class WarehouseManagerRepositoryTest {
	
	@Autowired
	WarehouseManagerRepository warehouseManagerRepo;
	
	@Autowired
	WarehouseRepository warehouseRepo;
	
	@Test
	public void findByWarehouseTest() {
		Warehouses wh = new Warehouses();
		WarehouseManager manager = new WarehouseManager();
		List<WarehouseManager> managers = new ArrayList<WarehouseManager>();
		managers.add(manager);
		manager.setWarehouse(wh);
		wh.setManager(managers);
		
		wh = warehouseRepo.save(wh);
		warehouseManagerRepo.save(manager);
		
		WarehouseManager whman = warehouseManagerRepo.findByWarehouse(wh.getId());
		assertNotNull(whman, "warehouseManagerRepo should find warehouseManager by warehouse");
	}
}