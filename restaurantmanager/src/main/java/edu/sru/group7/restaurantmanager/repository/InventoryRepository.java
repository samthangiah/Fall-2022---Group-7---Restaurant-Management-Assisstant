package edu.sru.group7.restaurantmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import edu.sru.group7.restaurantmanager.domain.Inventory;

@Service
public interface InventoryRepository extends CrudRepository<Inventory, Long> {

	@Query(value="SELECT * FROM restaurantmanagerdb.inventory WHERE restaurant_id = :id", nativeQuery=true)
	public List<Inventory> findInventoryRestaurant(long id);

	@Query(value="SELECT * FROM restaurantmanagerdb.inventory WHERE warehouse_id = :id", nativeQuery=true)
	public List<Inventory> findInventoryWarehouse(long id);
	
	
}