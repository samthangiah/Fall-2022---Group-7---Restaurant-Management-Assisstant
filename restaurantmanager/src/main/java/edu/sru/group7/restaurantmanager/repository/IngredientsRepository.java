package edu.sru.group7.restaurantmanager.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import edu.sru.group7.restaurantmanager.domain.Ingredients;

@Service
public interface IngredientsRepository extends CrudRepository<Ingredients, Long> {

	@Query(value="SELECT * FROM restaurantmanagerdb.ingredients WHERE menu_id = :id", nativeQuery=true)
	Ingredients findByMenuItem(long id);

}