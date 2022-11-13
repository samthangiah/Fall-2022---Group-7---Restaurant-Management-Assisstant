package edu.sru.group7.restaurantmanager.authentication;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import edu.sru.group7.restaurantmanager.security.ApplicationUserRole;

import edu.sru.group7.restaurantmanager.domain.Customers;
import edu.sru.group7.restaurantmanager.repository.CustomerRepository;
import edu.sru.group7.restaurantmanager.domain.Admins;
import edu.sru.group7.restaurantmanager.repository.AdminRepository;
import edu.sru.group7.restaurantmanager.domain.Managers;
import edu.sru.group7.restaurantmanager.repository.ManagerRepository;
import edu.sru.group7.restaurantmanager.domain.Servers;
import edu.sru.group7.restaurantmanager.domain.WarehouseManager;
import edu.sru.group7.restaurantmanager.repository.ServerRepository;
import edu.sru.group7.restaurantmanager.repository.WarehouseManagerRepository;


@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao {

	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private AdminRepository adminRepo;
	
	@Autowired
	private ManagerRepository managerRepo;
	
	@Autowired
	private ServerRepository serverRepo;
	
	@Autowired
	private WarehouseManagerRepository warehouseManagerRepo;
	
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
					ApplicationUserRole.HQADMIN.getGrantedAuthorities(),
					true,
					true,
					true,
					true));
		
		applicationUsers.add(new ApplicationUser(
					"hqmanager@email.com",
					passwordEncoder.encode("pass"),
					ApplicationUserRole.HQMANAGER.getGrantedAuthorities(),
					true,
					true,
					true,
					true));
		
		//Loading from databases as ApplicationUsers
		for (Customers customer : customerRepo.findAll()) {
			applicationUsers.add(new ApplicationUser(
					customer.getEmail(),
					passwordEncoder.encode(customer.getPassword()),
					ApplicationUserRole.CUSTOMER.getGrantedAuthorities(),
					true,
					true,
					true,
					true));
		}
		for (Admins admin : adminRepo.findAll()) {
			applicationUsers.add(new ApplicationUser(
					admin.getEmail(),
					passwordEncoder.encode(admin.getPassword()),
					ApplicationUserRole.ADMIN.getGrantedAuthorities(),
					true,
					true,
					true,
					true));
		}
		for (Managers manager : managerRepo.findAll()) {
			applicationUsers.add(new ApplicationUser(
					manager.getEmail(),
					passwordEncoder.encode(manager.getPassword()),
					ApplicationUserRole.MANAGER.getGrantedAuthorities(),
					true,
					true,
					true,
					true));
		}
		for (Servers server : serverRepo.findAll()) {
			applicationUsers.add(new ApplicationUser(
					server.getEmail(),
					passwordEncoder.encode(server.getPassword()),
					ApplicationUserRole.SERVER.getGrantedAuthorities(),
					true,
					true,
					true,
					true));
		}
		for (WarehouseManager warehouseManager : warehouseManagerRepo.findAll()) {
			applicationUsers.add(new ApplicationUser(
					warehouseManager.getEmail(),
					passwordEncoder.encode(warehouseManager.getPassword()),
					ApplicationUserRole.WAREHOUSEMANAGER.getGrantedAuthorities(),
					true,
					true,
					true,
					true));
		}
		
		return applicationUsers;
	}
	
	public String encode(String password) {
		return passwordEncoder.encode(password);
	}

}
