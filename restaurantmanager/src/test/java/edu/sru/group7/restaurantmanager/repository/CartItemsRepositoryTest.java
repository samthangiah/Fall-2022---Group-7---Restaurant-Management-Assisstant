package edu.sru.group7.restaurantmanager.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.sru.group7.restaurantmanager.domain.CartItems;
import edu.sru.group7.restaurantmanager.domain.Customers;
import edu.sru.group7.restaurantmanager.domain.Menu;


/**
 * Test class for CartItems repository class
 */
@SpringBootTest
public class CartItemsRepositoryTest {
	
	@Autowired
	CartItemsRepository cartItemsRepo;
	
	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	MenuRepository menuRepo;
	
	@BeforeEach
	public void setup() {
		customerRepo.deleteAll();
		menuRepo.deleteAll();
		cartItemsRepo.deleteAll();
		
		Customers cust = new Customers();
		cust.setEmail("email@email.com");
		Menu menu = new Menu();
		menu.setId(1);
		CartItems item = new CartItems(menu, cust, 1);
		item.setCustomer_id(cust);
		
		customerRepo.save(cust);
		menuRepo.save(menu);
		cartItemsRepo.save(item);
	}
	
	@Test
	public void findByCustomerTest() {
		Customers cust = customerRepo.findByEmail("email@email.com");
		
		List<CartItems> items = cartItemsRepo.findByCustomer(cust);
		assertNotNull(items, "cartItemsRepo should not return null");
		assertFalse(items.isEmpty(), "cartItemsRepo should find item by customer");
	}
	
	@Test
	public void findByCustMenuIdTest() {
		Customers cust = customerRepo.findByEmail("email@email.com");
		
		CartItems item = cartItemsRepo.findByCustMenuId(1, cust.getId());
		assertNotNull(item, "item should not be null");
	}
}