package edu.sru.group7.restaurantmanager.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import edu.sru.group7.restaurantmanager.domain.Warehouses;

@Service
public interface WarehouseRepository extends CrudRepository<Warehouses, Long> {
	
	
}
