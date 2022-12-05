package edu.sru.group7.restaurantmanager.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import edu.sru.group7.restaurantmanager.domain.Admins;

@Service
/**
 * Repository For Local Admins
 */
public interface AdminRepository extends CrudRepository<Admins, Long> {

	/**
	 * Finds admins by their email
	 * @param email 
	 * @return admin
	 */
	public Admins findAdminByEmail(String email);

}



