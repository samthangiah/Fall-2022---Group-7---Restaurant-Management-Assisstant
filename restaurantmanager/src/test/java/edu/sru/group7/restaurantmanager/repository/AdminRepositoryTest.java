package edu.sru.group7.restaurantmanager.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.sru.group7.restaurantmanager.domain.Admins;

/**
 * Test class for Admin repository class
 */
@SpringBootTest
public class AdminRepositoryTest {
	
	@Autowired
	AdminRepository adminRepo;
	
	@Test
	public void findAdminByEmailTest() {
		adminRepo.deleteAll();
		Admins admin = new Admins();
		admin.setEmail("email@email.com");
		adminRepo.save(admin);
		
		assertNotNull(adminRepo.findAdminByEmail("email@email.com"), "adminRepo should find admin by email");
	}
}