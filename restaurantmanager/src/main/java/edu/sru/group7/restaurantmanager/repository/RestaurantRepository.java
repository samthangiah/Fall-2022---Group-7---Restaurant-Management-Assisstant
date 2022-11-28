package edu.sru.group7.restaurantmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import edu.sru.group7.restaurantmanager.domain.Restaurants;

/**
 * Restaurant Repository
 */
@Service
public interface RestaurantRepository extends CrudRepository<Restaurants, Long> {
	
	/**
	 * @param id Admin
	 * @return List of Restaurants belonging to Admin id parameter.
	 */
	@Query(value="SELECT * FROM restaurantmanagerdb.restaurants WHERE admin_id = :id", nativeQuery=true)
	public List<Restaurants> findByAdmin(long id);

	/**
	 * @return All restaurants without any admins assigned.
	 */
	@Query(value="SELECT * FROM restaurantmanagerdb.restaurants WHERE admin_id IS NULL", nativeQuery=true)
	public List<Restaurants> findMissingAdmin();

}
