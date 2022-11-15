package edu.sru.group7.restaurantmanager.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import edu.sru.group7.restaurantmanager.domain.StateTax;

@Service
public interface StateTaxRepository extends CrudRepository<StateTax, Long> {
	
	/**
	 * @param s State
	 * @return StateTax object associated with state
	 */
	@Query(value="SELECT * FROM restaurantmanagerdb.state_tax WHERE state = :s", nativeQuery=true)
	public StateTax findByState(String s);
}