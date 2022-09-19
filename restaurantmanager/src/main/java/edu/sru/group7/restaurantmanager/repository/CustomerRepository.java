package edu.sru.group7.restaurantmanager.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import edu.sru.group7.restaurantmanager.domain.Customers;

@Service
public interface CustomerRepository extends CrudRepository<Customers, Long> {
	
	
}