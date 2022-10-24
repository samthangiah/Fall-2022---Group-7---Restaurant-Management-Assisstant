package edu.sru.group7.restaurantmanager.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import edu.sru.group7.restaurantmanager.domain.Managers;
import edu.sru.group7.restaurantmanager.domain.Restaurants;

@Service
public interface ManagerRepository extends CrudRepository<Managers, Long> {

	@Query(value="SELECT * FROM restaurantmanagerdb.managers WHERE restaurant_id = :id", nativeQuery=true)
	public Restaurants findByRestaurant(long id);
	
}

