package edu.sru.group7.restaurantmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import edu.sru.group7.restaurantmanager.domain.CartItems;
import edu.sru.group7.restaurantmanager.domain.Customers;

/**
 * CRUD interface with CartItems POJOs
 */
@Service
public interface CartItemsRepository extends CrudRepository<CartItems, Long> {

	/**
	 * @param id Customer ID
	 * @return List of cart Items belonging to Customer POJO
	 */
	@Query(value="SELECT * FROM restaurantmanagerdb.cart_items WHERE customer_id = :id", nativeQuery=true)
	public List<CartItems> findByCustomer(Customers id);

	/**
	 * 
	 * @param id Menu ID
	 * @param customer Customer ID
	 * @return Menu item belonging to customer in cart. Decider on whether to increase quantity or add new CartItems POJO.
	 */
	@Query(value="SELECT * FROM restaurantmanagerdb.cart_items WHERE menu_id = :id AND customer_id = :customer", nativeQuery=true)
	public CartItems findByCustMenuId(long id, long customer);
}
