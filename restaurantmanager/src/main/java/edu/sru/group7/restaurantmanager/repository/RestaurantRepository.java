package edu.sru.group7.restaurantmanager.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import edu.sru.group7.restaurantmanager.domain.Restaurants;

@Service
public interface RestaurantRepository extends CrudRepository<Restaurants, Long> {
	
	@Query(value="SELECT * FROM restaurantmanagerdb.restaurants WHERE admin_id = :id", nativeQuery=true)
	public Restaurants findByAdmin(long id);

}
