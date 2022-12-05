package edu.sru.group7.restaurantmanager.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import edu.sru.group7.restaurantmanager.domain.Customers;

/**
 * CRUD interface for Customers POJO
 */
@Service
public interface CustomerRepository extends CrudRepository<Customers, Long> {
	
	/**
	 * Finds all customers matching the email parameter.
	 * @param email
	 * @return
	 */
	@Query(value ="SELECT * FROM restaurantmanagerdb.customers WHERE email = :email", nativeQuery=true)
	public Customers findByEmail(String email);
}