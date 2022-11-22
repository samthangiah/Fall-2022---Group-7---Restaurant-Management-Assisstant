package edu.sru.group7.restaurantmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import edu.sru.group7.restaurantmanager.domain.Customers;
import edu.sru.group7.restaurantmanager.domain.Log;

@Service
public interface LogRepository extends CrudRepository<Log, Long> {

	@Query(value="SELECT * FROM restaurantmanagerdb.log WHERE user_id = :cust", nativeQuery=true)
	List<Log> findByID(Customers cust);

	
	
}
