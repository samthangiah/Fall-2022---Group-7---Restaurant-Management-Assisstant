package edu.sru.group7.restaurantmanager.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test class for PaymentDetails_Form class
 */
@SpringBootTest
public class PaymentDetails_FormTest {
	
	@Test
	public void gettersAndSettersTests() {
		PaymentDetails_Form paymentDetails_form = new PaymentDetails_Form();
		
		paymentDetails_form.setCardholderName("test");
		assertEquals("test", paymentDetails_form.getCardholderName(), "paymentDetails_form getCardholderName should return cardholderName");
		paymentDetails_form.setCardholderName("name");
		assertEquals("name", paymentDetails_form.getCardholderName(), "paymentDetails_form setCardholderName should set cardholderName property");
		
		paymentDetails_form.setCardType("test");
		assertEquals("test", paymentDetails_form.getCardType(), "paymentDetails_form getCardType should return cardType");
		paymentDetails_form.setCardType("mastercard");
		assertEquals("mastercard", paymentDetails_form.getCardType(), "paymentDetails_form setCardType should set cartType property");
		
		paymentDetails_form.setCardNumber("test");
		assertEquals("test", paymentDetails_form.getCardNumber(), "paymentDetails_form getCardNumber should return cardNumber");
		paymentDetails_form.setCardNumber("1234567890");
		assertEquals("1234567890", paymentDetails_form.getCardNumber(), "paymentDetails_form setCardNumber should set cardNumber property");
		
		paymentDetails_form.setExpirationDate("test");
		assertEquals("test", paymentDetails_form.getExpirationDate(), "paymentDetails_form getExpirationDate should return expirationDate");
		paymentDetails_form.setExpirationDate("2023-01-01");
		assertEquals("2023-01-01", paymentDetails_form.getExpirationDate(), "paymentDetails_form setExpirationDate should set expirationDate property");
		
		paymentDetails_form.setSecurityCode("test");
		assertEquals("test", paymentDetails_form.getSecurityCode(), "paymentDetails_form getSecurityCode should return securityCode");
		paymentDetails_form.setSecurityCode("123");
		assertEquals("123", paymentDetails_form.getSecurityCode(), "paymentDetails_form setSecurityCode should set securityCode property");
		
		paymentDetails_form.setPostalCode("test");
		assertEquals("test", paymentDetails_form.getPostalCode(), "paymentDetails_form getPostalCode should return postalCode");
		paymentDetails_form.setPostalCode("12345");
		assertEquals("12345", paymentDetails_form.getPostalCode(), "paymentDetails_form setPostalCode should set postalCode property");
	}
}
