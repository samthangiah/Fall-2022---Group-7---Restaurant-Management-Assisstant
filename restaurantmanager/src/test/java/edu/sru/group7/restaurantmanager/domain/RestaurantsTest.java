package edu.sru.group7.restaurantmanager.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test class for Restaurants class
 */
@SpringBootTest
public class RestaurantsTest {
	
	@Test
	public void gettersAndSettersTests() {
		Restaurants restaurant = new Restaurants();
		
		restaurant.setId(1);
		assertEquals((long) 1, restaurant.getId(), "restaurant getId should return Id");
		restaurant.setId(2);
		assertEquals((long) 2, restaurant.getId(), "restaurant setId should set Id property");
		
		restaurant.setAddress("test");
		assertEquals("test", restaurant.getAddress(), "restaurant getAddress should return address");
		restaurant.setAddress("address");
		assertEquals("address", restaurant.getAddress(), "restaurant setAddress should set address property");
		
		restaurant.setState("test");
		assertEquals("test", restaurant.getState(), "restaurant getState should return state");
		restaurant.setState("PA");
		assertEquals("PA", restaurant.getState(), "restaurant setState should set state property");
		
		restaurant.setZipcode("test");
		assertEquals("test", restaurant.getZipcode(), "restaurant getZipcode should return zipcode");
		restaurant.setZipcode("12345");
		assertEquals("12345", restaurant.getZipcode(), "restaurant setZipcode should set zipcode property");
		
		restaurant.setCity("test");
		assertEquals("test", restaurant.getCity(), "restaurant getCity should return city");
		restaurant.setCity("slippery rock");
		assertEquals("slippery rock", restaurant.getCity(), "restaurant setCity should set city property");
		
		restaurant.setSales(0.00F);
		assertEquals(0.00F, restaurant.getSales(), "restaurant getSales should return sales");
		restaurant.setSales(1000.00F);
		assertEquals(1000.00F, restaurant.getSales(), "restaurant setSales should set sales property");
		
		restaurant.setProfits(0.00F);
		assertEquals(0.00F, restaurant.getProfits(), "restaurant getProfits should return profits");
		restaurant.setProfits(700.00F);
		assertEquals(700.00F, restaurant.getProfits(), "restaurant setProfits should set profits property");
		
		Admins admin = new Admins();
		Orders order = new Orders();
		List<Orders> orders = new ArrayList<Orders>();
		orders.add(order);
		Servers server = new Servers();
		List<Servers> servers = new ArrayList<Servers>();
		servers.add(server);
		Managers manager = new Managers();
		List<Managers> managers = new ArrayList<Managers>();
		managers.add(manager);
		Shipping shipment = new Shipping();
		List<Shipping> shipments = new ArrayList<Shipping>();
		shipments.add(shipment);
		
		restaurant.setAdmin(admin);
		assertEquals(admin, restaurant.getAdmin(), "restaurant getAdmin should return admin");
		restaurant.setAdmin(null);
		assertNull(restaurant.getAdmin(), "restaurant setAdmin should set admin property");
		
		restaurant.setOrder(orders);
		assertEquals(orders, restaurant.getOrder(), "restaurant getOrder should return orders");
		restaurant.setOrder(null);
		assertNull(restaurant.getOrder(), "restaurant setOrder should set orders property");
		
		restaurant.setServers(servers);
		assertEquals(servers, restaurant.getServers(), "restaurant getServers should return servers");
		restaurant.setServers(null);
		assertNull(restaurant.getServers(), "restaurant setServers should set servers property");
		
		restaurant.setManagers(managers);
		assertEquals(managers, restaurant.getManagers(), "restaurant getManagers should return managers");
		restaurant.setManagers(null);
		assertNull(restaurant.getManagers(), "restaurant setManagers should set managers property");
		
		restaurant.setShipments(shipments);
		assertEquals(shipments, restaurant.getShipments(), "restaurant getShipments should return shipments");
		restaurant.setShipments(null);
		assertNull(restaurant.getShipments(), "restaurant setShipments should set shipments property");
		
		assertEquals("Restaurant ID: 2 Address: address", restaurant.toString(), "restaurant toString should return string id and address");
	}
}
