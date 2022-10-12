package edu.sru.group7.restaurantmanager.authentication;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import edu.sru.group7.restaurantmanager.security.ApplicationUserRole;

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao {

	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
		super();
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
		return getApplicationUsers()
				.stream()
				.filter(applicationUser -> username.equals(applicationUser.getUsername()))
				.findFirst();
	}

	private List<ApplicationUser> getApplicationUsers() {
		List<ApplicationUser> applicationUsers = com.google.common.collect.Lists.newArrayList(
						new ApplicationUser(
								"sam",
								passwordEncoder.encode("thangiah"),
								ApplicationUserRole.ADMIN.getGrantedAuthorities(),
								true,
								true,
								true,
								true
								)
						);
		
		
		
		return applicationUsers;
	}

}
