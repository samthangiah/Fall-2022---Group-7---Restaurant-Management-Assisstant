package edu.sru.group7.restaurantmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import edu.sru.group7.restaurantmanager.domain.Servers;

@Service
public interface ServerRepository extends CrudRepository<Servers, Long> {
	
	@Query(value="SELECT * FROM restaurantmanagerdb.servers WHERE restaurant_id = :id", nativeQuery=true)
	public List<Servers> findServerLocation(long id);
}
