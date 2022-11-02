package edu.sru.group7.restaurantmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import edu.sru.group7.restaurantmanager.domain.CartItems;
import edu.sru.group7.restaurantmanager.domain.Customers;

@Service
public interface CartItemsRepository extends CrudRepository<CartItems, Long> {

	@Query(value="SELECT * FROM restaurantmanagerdb.cart_items WHERE customer_id = :id", nativeQuery=true)
	public List<CartItems> findByCustomer(Customers id);
	
	
}
