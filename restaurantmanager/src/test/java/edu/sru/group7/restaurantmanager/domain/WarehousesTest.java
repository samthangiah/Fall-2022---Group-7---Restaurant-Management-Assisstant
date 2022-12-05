package edu.sru.group7.restaurantmanager.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test class for Warehouses class
 */
@SpringBootTest
public class WarehousesTest {
	
	@Test
	public void gettersAndSettersTests() {
		Warehouses warehouse = new Warehouses();
		
		warehouse.setId(1);
		assertEquals((long) 1, warehouse.getId(), "warehouse getId should return Id");
		warehouse.setId(2);
		assertEquals((long) 2, warehouse.getId(), "warehouse setId should set Id property");
		
		warehouse.setAddress("test");
		assertEquals("test", warehouse.getAddress(), "warehouse getAddress should return address");
		warehouse.setAddress("address");
		assertEquals("address", warehouse.getAddress(), "warehouse setAddress should set address property");
		
		warehouse.setState("test");
		assertEquals("test", warehouse.getState(), "warehouse getState should return state");
		warehouse.setState("PA");
		assertEquals("PA", warehouse.getState(), "warehouse setState should set state property");
		
		warehouse.setZipcode("test");
		assertEquals("test", warehouse.getZipcode(), "warehouse getZipcode should return zipcode");
		warehouse.setZipcode("12345");
		assertEquals("12345", warehouse.getZipcode(), "warehouse setZipcode should set zipcode property");
		
		warehouse.setCity("test");
		assertEquals("test", warehouse.getCity(), "warehouse getCity should return city");
		warehouse.setCity("slippery rock");
		assertEquals("slippery rock", warehouse.getCity(), "warehouse setCity should set city property");
		
		Inventory inventory = new Inventory();
		List<Inventory> inventories = new ArrayList<Inventory>();
		inventories.add(inventory);
		Shipping shipment = new Shipping();
		List<Shipping> shipments = new ArrayList<Shipping>();
		shipments.add(shipment);
		WarehouseManager manager = new WarehouseManager();
		List<WarehouseManager> managers = new ArrayList<WarehouseManager>();
		managers.add(manager);
		WarehouseEmployees employee = new WarehouseEmployees();
		List<WarehouseEmployees> employees = new ArrayList<WarehouseEmployees>();
		employees.add(employee);
		Admins admin = new Admins();
		
		warehouse.setInventory(inventories);
		assertEquals(inventories, warehouse.getInventory(), "warehouse getInventory should return inventories");
		warehouse.setInventory(null);
		assertNull(warehouse.getInventory(), "warehouse setInventory should set inventory property");
		
		warehouse.setShipments(shipments);
		assertEquals(shipments, warehouse.getShipments(), "warehouse getShipments should return shipments");
		warehouse.setShipments(null);
		assertNull(warehouse.getShipments(), "warehouse setShipments should set shipments property");
		
		warehouse.setManager(managers);
		assertEquals(managers, warehouse.getManager(), "warehouse getManager should return warehouse managers");
		warehouse.setManager(null);
		assertNull(warehouse.getManager(), "warehouse setManager should set manager property");
		
		warehouse.setEmployees(employees);
		assertEquals(employees, warehouse.getEmployees(), "warehouse getEmployees should return employees");
		warehouse.setEmployees(null);
		assertNull(warehouse.getEmployees(), "warehouse setEmployees should set employees property");
		
		warehouse.setAdmin(admin);
		assertEquals(admin, warehouse.getAdmin(), "warehouse getAdmin should return admin");
		warehouse.setAdmin(null);
		assertNull(warehouse.getAdmin(), "warehouse setAdmin should set admin property");
		
		assertEquals("Warehouse ID: 2 Address: address", warehouse.toString(), "warehouse toString should return string id and address");
	}
}
