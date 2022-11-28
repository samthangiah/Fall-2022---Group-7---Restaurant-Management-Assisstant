package edu.sru.group7.restaurantmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import edu.sru.group7.restaurantmanager.domain.Inventory;

/**
 * Inventory Repository
 */
@Service
public interface InventoryRepository extends CrudRepository<Inventory, Long> {

	/**
	 * @param id Restaurant ID
	 * @return All Inventory Items belonging to Restaurant ID in parameter.
	 */
	@Query(value="SELECT * FROM restaurantmanagerdb.inventory WHERE restaurant_id = :id", nativeQuery=true)
	public List<Inventory> findInventoryRestaurant(long id);

	/**
	 * @param id Warehouse ID
	 * @return All Inventory Items belonging to Warehouse ID in parameter.
	 */
	@Query(value="SELECT * FROM restaurantmanagerdb.inventory WHERE warehouse_id = :id", nativeQuery=true)
	public List<Inventory> findInventoryWarehouse(long id);
	
	
}