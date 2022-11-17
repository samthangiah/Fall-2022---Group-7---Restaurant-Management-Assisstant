package edu.sru.group7.restaurantmanager.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import edu.sru.group7.restaurantmanager.domain.StateTax;

@Service
public interface StateTaxRepository extends CrudRepository<StateTax, Long> {
	
	/**
	 * @param s State
	 * @return StateTax object associated with state. null if not found
	 */
	@Query(value="SELECT * FROM restaurantmanagerdb.state_tax WHERE STRCMP(state, :s) = 0", nativeQuery=true)
	public StateTax findByState(String s);
}