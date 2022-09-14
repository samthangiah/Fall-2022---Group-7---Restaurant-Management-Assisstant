package edu.sru.group7.restaurantmanager.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import edu.sru.group7.restaurantmanager.domain.Customers;
import edu.sru.group7.restaurantmanager.domain.Servers;

@Service
public interface ServerRepository extends CrudRepository<Servers, Long> {
	
	List<Servers> findByLocation(int location);
}
