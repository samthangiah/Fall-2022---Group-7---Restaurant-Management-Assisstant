package edu.sru.group7.restaurantmanager.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test class for Customers class
 */
@SpringBootTest
public class CustomersTest {
	
	@Test
	public void gettersAndSettersTests() {
		Customers customer = new Customers();
		
		customer.setId(1);
		assertEquals((long) 1, customer.getId(), "customer getId should return Id");
		customer.setId(2);
		assertEquals((long) 2, customer.getId(), "customer setId should set Id property");
		
		customer.setFirstName("test");
		assertEquals("test", customer.getFirstName(), "customer getFirstName should return firstName");
		customer.setFirstName("firstName");
		assertEquals("firstName", customer.getFirstName(), "customer setFirstName should set firstName property");
		
		customer.setLastName("test");
		assertEquals("test", customer.getLastName(), "customer getLastName should return lastName");
		customer.setLastName("lastName");
		assertEquals("lastName", customer.getLastName(), "customer setLastName should set lastName property");
		
		customer.setEmail("test");
		assertEquals("test", customer.getEmail(), "customer getEmail should return email");
		customer.setEmail("email@email.com");
		assertEquals("email@email.com", customer.getEmail(), "customer setEmail should set email property");
		
		customer.setPassword("test");
		assertEquals("test", customer.getPassword(), "customer getPassword should return password");
		customer.setPassword("password");
		assertEquals("password", customer.getPassword(), "customer setPassword should set password property");
		
		customer.setRewardsMember(false);
		assertFalse(customer.getRewardsMember(), "customer getRewardsMember should return rewardsMember");
		customer.setRewardsMember(true);
		assertTrue(customer.getRewardsMember(), "customer setRewardsMember should set rewardsMember property");
		
		customer.setRewardsAvailable(0);
		assertEquals(0, customer.getRewardsAvailable(), "customer getRewardsAvailable should return rewardsAvailable");
		customer.setRewardsAvailable(1);
		assertEquals(1, customer.getRewardsAvailable(), "customer setRewardsAvailable should set rewardsAvailable property");
		
		customer.setLocation("test");
		assertEquals("test", customer.getLocation(), "customer getLocation should return location");
		customer.setLocation("restaurant address");
		assertEquals("restaurant address", customer.getLocation(), "customer setLocation should set location property");
		
		Orders order = new Orders();
		List<Orders> orders = new ArrayList<Orders>();
		orders.add(order);
		CartItems cartItem = new CartItems();
		Set<CartItems> cartItems = new HashSet<CartItems>();
		cartItems.add(cartItem);
		
		customer.setOrderHistory(orders);
		assertEquals(orders, customer.getOrderHistory(), "customer getOrderHistory should return orders");
		customer.setOrderHistory(null);
		assertNull(customer.getOrderHistory(), "customer setOrderHistory should set orderHistory property");
		
		customer.setCartItems(cartItems);
		assertEquals(cartItems, customer.getCartItems(), "customer getCartItems should return cartItems");
		customer.setCartItems(null);
		assertNull(customer.getCartItems(), "customer setCartItems should set cartItems property");
		
		assertEquals("Name: firstName lastName, Email: email@email.com", customer.toString(), "customer toString should return string full name and email");
	}
}
