package edu.sru.group7.restaurantmanager.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import edu.sru.group7.restaurantmanager.domain.Shipping;

@Service
public interface ShippingRepository extends CrudRepository<Shipping, Long> {

	@Query(value="SELECT * FROM restaurantmanagerdb.shipping WHERE manager_id = :id", nativeQuery=true)
	List<Shipping> findRestaurantShipment(long id);

	@Query(value="SELECT * FROM restaurantmanagerdb.shipping WHERE warehouse_id = :id ORDER BY status Desc", nativeQuery=true)
	List<Shipping> findWarehouseManShipment(long id);
	
	@Query(value="SELECT * FROM restaurantmanagerdb.shipping WHERE warehouse_id = :id ORDER BY status", nativeQuery=true)
	List<Shipping> findAdminShipment(long id);
	
}
