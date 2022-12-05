package edu.sru.group7.restaurantmanager.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.sru.group7.restaurantmanager.domain.Customers;
import edu.sru.group7.restaurantmanager.domain.Orders;
import edu.sru.group7.restaurantmanager.domain.Restaurants;

/**
 * Test class for Order repository class
 */
@SpringBootTest
public class OrderRepositoryTest {
	
	@Autowired
	OrderRepository orderRepo;
	
	@Autowired
	RestaurantRepository restaurantRepo;
	
	@Autowired
	CustomerRepository customerRepo;
	
	@BeforeEach
	public void setup() {
		customerRepo.deleteAll();
		orderRepo.deleteAll();
		restaurantRepo.deleteAll();
		
		Customers cust = new Customers();
		cust.setEmail("email@email.com");
		Restaurants rest = new Restaurants();
		Orders order = new Orders();
		order.setCustomer_id(cust);
		order.setRestaurant(rest);
		order.setStatus("Pending Payment");
		
		Orders order2 = new Orders();
		order2.setRestaurant(rest);
		order2.setStatus("Paid");
		
		customerRepo.save(cust);
		restaurantRepo.save(rest);
		orderRepo.save(order);
		orderRepo.save(order2);
	}
	
	@Test
	public void findOrdersByLocationTest() {
		List<Restaurants> rests = (List<Restaurants>) restaurantRepo.findAll();
		List<Orders> orders = orderRepo.findOrdersByLocation(rests.get(0));
		assertNotNull(orders, "orderRepo should not be null");
		assertFalse(orders.isEmpty(), "orderRepo should not be empty");
	}
	
	@Test
	public void findByCustomerIdUnpaidTest() {
		Customers cust = customerRepo.findByEmail("email@email.com");
		Orders order = orderRepo.findByCustomerIdUnpaid(cust.getId());
		assertNotNull(order, "order should not be null");
	}
}