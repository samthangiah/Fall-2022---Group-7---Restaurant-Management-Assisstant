package edu.sru.group7.restaurantmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import edu.sru.group7.restaurantmanager.domain.Orders;
import edu.sru.group7.restaurantmanager.domain.Restaurants;

/**
 * CRUD interface for Orders POJO
 */
@Service
public interface OrderRepository extends CrudRepository<Orders, Long> {
	
	/**
	 * 
	 * @param id Restaurant Id
	 * @return List of Orders that have status 'Paid' at the Restaurant parameter.
	 */
	@Query(value="SELECT * FROM restaurantmanagerdb.orders WHERE restaurant = :rest AND status = 'Paid'", nativeQuery=true)
	public List<Orders> findOrdersByLocation(Restaurants rest);
	
	/**
	 * 
	 * @param id Customer Id
	 * @return Order of Customer parameter with status of 'Pending Payment'
	 */
	@Query(value="SELECT * FROM restaurantmanagerdb.orders WHERE customer_id = :id AND status = 'Pending Payment'", nativeQuery=true)
	public Orders findByCustomerIdUnpaid(long id);
}
