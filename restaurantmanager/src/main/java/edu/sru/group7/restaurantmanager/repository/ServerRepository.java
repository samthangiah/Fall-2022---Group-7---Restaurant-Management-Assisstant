package edu.sru.group7.restaurantmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import edu.sru.group7.restaurantmanager.domain.Restaurants;
import edu.sru.group7.restaurantmanager.domain.Servers;

/**
 * Server Repository
 */
@Service
public interface ServerRepository extends CrudRepository<Servers, Long> {
	
	/**
	 * @param rest Restaurant
	 * @return all servers belonging to Restaurant parameter
	 */
	@Query(value="SELECT * FROM restaurantmanagerdb.servers WHERE restaurant_id = :rest", nativeQuery=true)
	public List<Servers> findServerLocation(Restaurants rest);
}
