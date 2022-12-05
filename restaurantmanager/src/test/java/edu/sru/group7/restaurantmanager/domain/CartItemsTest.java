package edu.sru.group7.restaurantmanager.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test class for CartItems class
 */
@SpringBootTest
public class CartItemsTest {
	
	@Test
	public void gettersAndSettersTests() {
		CartItems cartItem = new CartItems();
		
		cartItem.setId(1);
		assertEquals((long) 1, cartItem.getId(), "cartItem getId should return Id");
		cartItem.setId(2);
		assertEquals((long) 2, cartItem.getId(), "cartItem setId should set Id property");
		
		Menu menu = new Menu();
		Customers customer = new Customers();
		
		cartItem.setMenu_id(menu);
		assertEquals(menu, cartItem.getMenu_id(), "cartItem getMenu_id should return menu");
		cartItem.setMenu_id(null);
		assertNull(cartItem.getMenu_id(), "cartItem setMenu_id should set menu property");
		
		cartItem.setCustomer_id(customer);
		assertEquals(customer, cartItem.getCustomer_id(), "cartItem getCustomer_id should return customer");
		cartItem.setCustomer_id(null);
		assertNull(cartItem.getCustomer_id(), "cartItem setCustomer_id should set customer property");
		
		cartItem.setQuantity(1);
		assertEquals(1, cartItem.getQuantity(), "cartItem getQuantity should return quantity");
		cartItem.setQuantity(2);
		assertEquals(2, cartItem.getQuantity(), "cartItem setQuantity should set quantity property");
	}
}
