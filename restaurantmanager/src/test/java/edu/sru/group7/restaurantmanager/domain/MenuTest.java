package edu.sru.group7.restaurantmanager.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test class for Menu class
 */
@SpringBootTest
public class MenuTest {
	
	@Test
	public void gettersAndSettersTests() {
		Menu menu = new Menu();
		
		menu.setId(1);
		assertEquals((long) 1, menu.getId(), "menu getId should return Id");
		menu.setId(2);
		assertEquals((long) 2, menu.getId(), "menu setId should set Id property");
		
		menu.setName("test");
		assertEquals("test", menu.getName(), "menu getName should return name");
		menu.setName("name");
		assertEquals("name", menu.getName(), "menu setName should set name property");
		
		menu.setEntree("test");
		assertEquals("test", menu.getEntree(), "menu getEntree should return entree");
		menu.setEntree("entree");
		assertEquals("entree", menu.getEntree(), "menu setEntree should set entree property");
		
		menu.setSides("test");
		assertEquals("test", menu.getSides(), "menu getSides should return sides");
		menu.setSides("sides");
		assertEquals("sides", menu.getSides(), "menu setSides should set sides property");
				
		menu.setPrice(0.00F);
		assertEquals(0.00F, menu.getPrice(), "menu getPrice should return price");
		menu.setPrice(1.99F);
		assertEquals(1.99F, menu.getPrice(), "menu setPrice should set price property");
		
		menu.setAvailability(false);
		assertFalse(menu.getAvailability(), "menu getAvailability should return availability");
		menu.setAvailability(true);
		assertTrue(menu.getAvailability(), "menu setAvailability should set availability property");
				
		menu.setQuantity(1);
		assertEquals(1, menu.getQuantity(), "menu getQuantity should return quantity");
		menu.setQuantity(2);
		assertEquals(2, menu.getQuantity(), "menu setQuantity should set quantity property");
		
		CartItems cartItem = new CartItems();
		Set<CartItems> cartItems = new HashSet<CartItems>();
		cartItems.add(cartItem);
		
		menu.setCartItems(cartItems);
		assertEquals(cartItems, menu.getCartItems(), "menu getCartItems should return cartItems");
		menu.setCartItems(null);
		assertNull(menu.getCartItems(), "menu setCartItems should set cartItems property");
		
		assertEquals("[name=name, price=1.99]", menu.toString(), "menu toString should return string name and price");
	}
}
