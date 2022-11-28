package edu.sru.group7.restaurantmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import edu.sru.group7.restaurantmanager.domain.Restaurants;
import edu.sru.group7.restaurantmanager.domain.Warehouses;

/**
 * Warehouse Repository
 */
@Service
public interface WarehouseRepository extends CrudRepository<Warehouses, Long> {

	/**
	 * @param id Admin 
	 * @return All warehouses assigned to Admin parameter
	 */
	@Query(value="SELECT * FROM restaurantmanagerdb.warehouses WHERE admin_id = :id", nativeQuery=true)
	List<Warehouses> findByAdmin(long id);
	
	
}
