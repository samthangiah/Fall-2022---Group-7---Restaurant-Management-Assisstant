package edu.sru.group7.restaurantmanager.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.sru.group7.restaurantmanager.domain.Restaurants;
import edu.sru.group7.restaurantmanager.domain.Servers;

/**
 * Test class for Servers repository class
 */
@SpringBootTest
public class ServerRepositoryTest {
	
	@Autowired
	ServerRepository serverRepo;
		
	@Autowired
	RestaurantRepository restaurantRepo;
	
	@Test
	public void findServerLocationTest() {
		Servers server = new Servers();
		List<Servers> s = new ArrayList<Servers>();
		s.add(server);
		Restaurants rest = new Restaurants();
		rest.setServers(s);
		server.setRestaurant(rest);
		rest = restaurantRepo.save(rest);
		serverRepo.save(server);
		
		List<Servers> servers = serverRepo.findServerLocation(rest);
		assertNotNull(servers, "serverRepo should not return null");
		assertFalse(servers.isEmpty(), "serverRepo should not be empty");
	}
}