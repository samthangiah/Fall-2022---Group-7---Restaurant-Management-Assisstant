package edu.sru.group7.restaurantmanager.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.sru.group7.restaurantmanager.domain.WarehouseEmployees;
import edu.sru.group7.restaurantmanager.domain.Warehouses;

/**
 * Test class for WarehouseEmployee repository class
 */
@SpringBootTest
public class WarehouseEmployeeRepositoryTest {
	
	@Autowired
	WarehouseEmployeeRepository warehouseEmployeeRepo;
	
	@Autowired
	WarehouseRepository warehouseRepo;
	
	@BeforeEach
	public void setup() {
		warehouseEmployeeRepo.deleteAll();
		warehouseRepo.deleteAll();
		
		Warehouses wh = new Warehouses();
		WarehouseEmployees emp = new WarehouseEmployees();
		List<WarehouseEmployees> empList = new ArrayList<WarehouseEmployees>();
		empList.add(emp);
		emp.setWarehouse(wh);
		wh.setEmployees(empList);
		
		warehouseRepo.save(wh);
		warehouseEmployeeRepo.save(emp);
	}
	
	@Test
	public void findByWarehouseListTest() {
		List<Warehouses> wh = (List<Warehouses>) warehouseRepo.findAll();
		List<WarehouseEmployees> employees = warehouseEmployeeRepo.findByWarehouse(wh);
		assertNotNull(employees, "warehouseEmployeeRepo should not be null");
		assertFalse(employees.isEmpty(), "warehouseEmployeeRepo should not be empty");
	}
	
	@Test
	public void findByWarehouseTest() {
		List<Warehouses> wh = (List<Warehouses>) warehouseRepo.findAll();
		List<WarehouseEmployees> employees = warehouseEmployeeRepo.findByWarehouse(wh.get(0).getId());
		assertNotNull(employees, "warehouseEmployeeRepo should not be null");
		assertFalse(employees.isEmpty(), "warehouseEmployeeRepo should not be empty");
	}
}