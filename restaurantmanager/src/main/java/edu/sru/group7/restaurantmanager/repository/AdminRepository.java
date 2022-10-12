package edu.sru.group7.restaurantmanager.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import edu.sru.group7.restaurantmanager.authentication.ApplicationUser;
import edu.sru.group7.restaurantmanager.domain.Admins;

@Service
public interface AdminRepository extends CrudRepository<Admins, Long> {

	public Collection<? extends String> findAdminByEmail(String email);

}



