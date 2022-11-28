package edu.sru.group7.restaurantmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import edu.sru.group7.restaurantmanager.domain.WarehouseEmployees;
import edu.sru.group7.restaurantmanager.domain.Warehouses;

/**
 * WarehouseEmployee Repository
 */
@Service
public interface WarehouseEmployeeRepository extends CrudRepository<WarehouseEmployees, Long> {
	
	/**
	 * @param warehouse List<Warehouses>
	 * @return ALL warehouse employees belonging to the List of warehouse parameter.
	 */
	@Query(value="SELECT * FROM restaurantmanagerdb.warehouse_employees WHERE warehouse_id = :warehouse", nativeQuery=true)
	List<WarehouseEmployees> findByWarehouse(List<Warehouses> warehouse);

	/**
	 * @param id Warehouse id
	 * @return All warehouse employees belonging to warehouse id parameter
	 */
	@Query(value="SELECT * FROM restaurantmanagerdb.warehouse_employees WHERE warehouse_id = :id", nativeQuery=true)
	List<WarehouseEmployees> findByWarehouse(long id);
	
}

