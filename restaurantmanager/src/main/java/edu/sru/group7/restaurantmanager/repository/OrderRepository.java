package edu.sru.group7.restaurantmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import edu.sru.group7.restaurantmanager.domain.Inventory;
import edu.sru.group7.restaurantmanager.domain.Orders;

@Service
public interface OrderRepository extends CrudRepository<Orders, Long> {
	
	@Query(value="SELECT * FROM restaurantmanagerdb.orders WHERE restaurant = :id", nativeQuery=true)
	public List<Orders> findOrdersByLocation(long id);
	
	@Query(value="SELECT menu_id FROM restaurantmanagerdb.orders_items WHERE order_id = :id", nativeQuery=true)
	public List<Orders> findMenuItems(long id);
}
