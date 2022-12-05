package edu.sru.group7.restaurantmanager.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.sru.group7.restaurantmanager.domain.Managers;
import edu.sru.group7.restaurantmanager.domain.Shipping;
import edu.sru.group7.restaurantmanager.domain.Warehouses;

/**
 * Test class for Shipping repository class
 */
@SpringBootTest
public class ShippingRepositoryTest {
	
	@Autowired
	ShippingRepository shippingRepo;
	
	@Autowired
	ManagerRepository managerRepo;
	
	@Autowired
	WarehouseRepository warehouseRepo;
	
	@BeforeEach
	public void setup() {
		shippingRepo.deleteAll();
		managerRepo.deleteAll();
		warehouseRepo.deleteAll();
		
		Warehouses wh = new Warehouses();
		Managers manager = new Managers();
		Shipping shipment = new Shipping();
		shipment.setManager_id(manager);
		shipment.setWarehouse_id(wh);
		
		managerRepo.save(manager);
		warehouseRepo.save(wh);
		shippingRepo.save(shipment);
	}
	
	@Test
	public void findRestaurantShipmentTest() {
		List<Managers> manager = (List<Managers>) managerRepo.findAll();
		List<Shipping> shipments = shippingRepo.findRestaurantShipment(manager.get(0).getId());
		assertNotNull(shipments, "shipments should not be null");
		assertFalse(shipments.isEmpty(), "shipments should not be empty");
	}
	
	@Test
	public void findWarehouseManShipmentTest() {
		List<Warehouses> wh = (List<Warehouses>) warehouseRepo.findAll();
		List<Shipping> shipments = shippingRepo.findRestaurantShipment(wh.get(0).getId());
		assertNotNull(shipments, "shipments should not be null");
	}
	
	@Test
	public void findAdminShipment() {
		List<Warehouses> wh = (List<Warehouses>) warehouseRepo.findAll();
		List<Shipping> shipments = shippingRepo.findRestaurantShipment(wh.get(0).getId());
		assertNotNull(shipments, "shipments should not be null");
	}
}