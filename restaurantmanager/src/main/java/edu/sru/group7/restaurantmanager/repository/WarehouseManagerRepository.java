package edu.sru.group7.restaurantmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import edu.sru.group7.restaurantmanager.domain.Shipping;
import edu.sru.group7.restaurantmanager.domain.WarehouseManager;

@Service
public interface WarehouseManagerRepository extends CrudRepository<WarehouseManager, Long> {
	
}
