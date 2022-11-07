package edu.sru.group7.restaurantmanager.repository;

import java.util.List;
import java.util.Vector;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import edu.sru.group7.restaurantmanager.domain.Customers;

/**
 * CRUD interface for Customers POJO
 */
@Service
public interface CustomerRepository extends CrudRepository<Customers, Long> {
	
	/**
	 * @param id Customer ID
	 * @return List of Customers assigned to a Restaurant
	 */
	@Query(value="SELECT customers.* FROM ((restaurantmanagerdb.customers INNER JOIN orders ON orders.restaurant = :id AND orders.customer_id = id)", nativeQuery=true)
	public List<Customers> findByOrderLocation(long id);
}