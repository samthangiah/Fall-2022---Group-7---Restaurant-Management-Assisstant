package edu.sru.group7.restaurantmanager.authentication;

import java.util.Optional;

public interface ApplicationUserDao {

	public Optional<ApplicationUser> selectApplicationUserByUsername(String username);
	
}
