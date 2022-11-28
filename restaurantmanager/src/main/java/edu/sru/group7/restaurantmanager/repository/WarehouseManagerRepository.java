package edu.sru.group7.restaurantmanager.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import edu.sru.group7.restaurantmanager.domain.WarehouseManager;
import edu.sru.group7.restaurantmanager.domain.Warehouses;

/**
 * WarehouseManager Repository
 */
@Service
public interface WarehouseManagerRepository extends CrudRepository<WarehouseManager, Long> {

	/**
	 * @param id Warehouse ID
	 * @return All warehouse managers belonging to warehouse id parameter
	 */
	@Query(value="SELECT * FROM restaurantmanagerdb.warehouse_manager WHERE warehouse_id = :id", nativeQuery=true)
	WarehouseManager findByWarehouse(long id);
	
}
