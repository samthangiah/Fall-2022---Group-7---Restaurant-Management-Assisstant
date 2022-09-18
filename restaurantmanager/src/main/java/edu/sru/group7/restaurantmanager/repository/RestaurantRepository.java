package edu.sru.group7.restaurantmanager.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import edu.sru.group7.restaurantmanager.domain.Restaurants;

@Service
public interface RestaurantRepository extends CrudRepository<Restaurants, Long> {
	
	
}
