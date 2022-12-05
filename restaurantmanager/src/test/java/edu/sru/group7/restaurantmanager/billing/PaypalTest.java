package edu.sru.group7.restaurantmanager.billing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import edu.sru.group7.restaurantmanager.domain.Paypal_Form;

/**
 * Test class for Paypal class
 */
@SpringBootTest
public class PaypalTest {
	
	@Test
	public void gettersAndSettersTests() {
		Paypal paypal = new Paypal();
		
		paypal.setPaypalLogin("test");
		assertEquals("test", paypal.getPaypalLogin(), "paypal getPaypalLogin should return paypalLogin");
		paypal.setPaypalLogin("email@email.com");
		assertEquals("email@email.com", paypal.getPaypalLogin(), "paypal setPaypalLogin should set paypalLogin property");
		
		paypal.setPaypalPassword("test");
		assertEquals("test", paypal.getPaypalPassword(), "paypal getPaypalPassword should return paypalPassword");
		paypal.setPaypalPassword("password");
		assertEquals("password", paypal.getPaypalPassword(), "paypal setPaypalPassword should set paypalPassword property");
	}
	
	@Test
	public void transferFieldsTest() {
		Paypal paypal1 = new Paypal();
		Paypal paypal2 = new Paypal();
		
		paypal1.setPaypalLogin("email@email.com");
		paypal1.setPaypalPassword("password");
		
		paypal2.transferFields(paypal1);
		
		assertEquals("email@email.com", paypal2.getPaypalLogin(), "paypal transferFields should transfer all fields");
		assertEquals("password", paypal2.getPaypalPassword(), "paypal transferFields should transfer all fields");
	}
	
	@Test
	public void buildFromFormTest() {
		Paypal paypal = new Paypal();
		Paypal_Form paypal_form = new Paypal_Form();
		
		paypal_form.setPaypalLogin("email@email.com");
		paypal_form.setPaypalPassword("password");
		
		paypal.buildFromForm(paypal_form);
		
		assertEquals("email@email.com", paypal.getPaypalLogin(), "paypal buildFromForm should build all fields");
		assertEquals("password", paypal.getPaypalPassword(), "paypal buildFromForm should build all fields");
	}
}
