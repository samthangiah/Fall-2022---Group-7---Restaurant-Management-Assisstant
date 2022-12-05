package edu.sru.group7.restaurantmanager.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test class for Paypal_Form class
 */
@SpringBootTest
public class Paypal_FormTest {
	
	@Test
	public void gettersAndSettersTests() {
		Paypal_Form paypal_form = new Paypal_Form();
		
		paypal_form.setPaypalLogin("test");
		assertEquals("test", paypal_form.getPaypalLogin(), "paypal_form getPaypalLogin should return paypalLogin");
		paypal_form.setPaypalLogin("email@email.com");
		assertEquals("email@email.com", paypal_form.getPaypalLogin(), "paypal_form setPaypalLogin should set paypalLogin property");
		
		paypal_form.setPaypalPassword("test");
		assertEquals("test", paypal_form.getPaypalPassword(), "paypal_form getPaypalPassword should return paypalPassword");
		paypal_form.setPaypalPassword("password");
		assertEquals("password", paypal_form.getPaypalPassword(), "paypal_form setPaypalPassword should set paypalPassword property");
	}
}
