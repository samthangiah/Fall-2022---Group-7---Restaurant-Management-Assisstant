package edu.sru.group7.restaurantmanager.billing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import edu.sru.group7.restaurantmanager.domain.PaymentDetails_Form;

/**
 * Test class for paymentDetails class
 */
@SpringBootTest
public class PaymentDetailsTest {
	
	@Test
	public void gettersAndSettersTests() {
		PaymentDetails paymentDetails = new PaymentDetails();
		
		paymentDetails.setCardholderName("test");
		assertEquals("test", paymentDetails.getCardholderName(), "paymentDetails getCardholderName should return cardholderName");
		paymentDetails.setCardholderName("name");
		assertEquals("name", paymentDetails.getCardholderName(), "paymentDetails setCardholderName should set cardholderName property");
		
		paymentDetails.setCardType("test");
		assertEquals("test", paymentDetails.getCardType(), "paymentDetails getCardType should return cardType");
		paymentDetails.setCardType("mastercard");
		assertEquals("mastercard", paymentDetails.getCardType(), "paymentDetails setCardType should set cartType property");
		
		paymentDetails.setCardNumber("test");
		assertEquals("test", paymentDetails.getCardNumber(), "paymentDetails getCardNumber should return cardNumber");
		paymentDetails.setCardNumber("1234567890");
		assertEquals("1234567890", paymentDetails.getCardNumber(), "paymentDetails setCardNumber should set cardNumber property");
		
		paymentDetails.setExpirationDate("test");
		assertEquals("test", paymentDetails.getExpirationDate(), "paymentDetails getExpirationDate should return expirationDate");
		paymentDetails.setExpirationDate("2023-01-01");
		assertEquals("2023-01-01", paymentDetails.getExpirationDate(), "paymentDetails setExpirationDate should set expirationDate property");
		
		paymentDetails.setSecurityCode("test");
		assertEquals("test", paymentDetails.getSecurityCode(), "paymentDetails getSecurityCode should return securityCode");
		paymentDetails.setSecurityCode("123");
		assertEquals("123", paymentDetails.getSecurityCode(), "paymentDetails setSecurityCode should set securityCode property");
		
		paymentDetails.setPostalCode("test");
		assertEquals("test", paymentDetails.getPostalCode(), "paymentDetails getPostalCode should return postalCode");
		paymentDetails.setPostalCode("12345");
		assertEquals("12345", paymentDetails.getPostalCode(), "paymentDetails setPostalCode should set postalCode property");
	}
	
	@Test
	public void transferFieldsTest() {
		PaymentDetails paymentDetails1 = new PaymentDetails();
		PaymentDetails paymentDetails2 = new PaymentDetails();
		
		paymentDetails1.setCardholderName("name");
		paymentDetails1.setCardNumber("1234567890");
		paymentDetails1.setCardType("mastercard");
		paymentDetails1.setExpirationDate("2023-01-01");
		paymentDetails1.setPostalCode("12345");
		paymentDetails1.setSecurityCode("123");
		
		paymentDetails2.transferFields(paymentDetails1);
		
		assertEquals("name", paymentDetails2.getCardholderName(), "paymentDetails transferFields should transfer all fields");
		assertEquals("1234567890", paymentDetails2.getCardNumber(), "paymentDetails transferFields should transfer all fields");
		assertEquals("mastercard", paymentDetails2.getCardType(), "paymentDetails transferFields should transfer all fields");
		assertEquals("2023-01-01", paymentDetails2.getExpirationDate(), "paymentDetails transferFields should transfer all fields");
		assertEquals("12345", paymentDetails2.getPostalCode(), "paymentDetails transferFields should transfer all fields");
		assertEquals("123", paymentDetails2.getSecurityCode(), "paymentDetails transferFields should transfer all fields");
	}
	
	@Test
	public void buildFromFormTest() {
		PaymentDetails paymentDetails = new PaymentDetails();
		PaymentDetails_Form paymentDetails_Form = new PaymentDetails_Form();
		
		paymentDetails_Form.setCardholderName("name");
		paymentDetails_Form.setCardNumber("1234567890");
		paymentDetails_Form.setCardType("mastercard");
		paymentDetails_Form.setExpirationDate("2023-01-01");
		paymentDetails_Form.setPostalCode("12345");
		paymentDetails_Form.setSecurityCode("123");
		
		paymentDetails.buildFromForm(paymentDetails_Form);
		
		assertEquals("name", paymentDetails.getCardholderName(), "paymentDetails buildFromForm should build all fields");
		assertEquals("1234567890", paymentDetails.getCardNumber(), "paymentDetails buildFromForm should build all fields");
		assertEquals("mastercard", paymentDetails.getCardType(), "paymentDetails buildFromForm should build all fields");
		assertEquals("2023-01-01", paymentDetails.getExpirationDate(), "paymentDetails buildFromForm should build all fields");
		assertEquals("12345", paymentDetails.getPostalCode(), "paymentDetails buildFromForm should build all fields");
		assertEquals("123", paymentDetails.getSecurityCode(), "paymentDetails buildFromForm should build all fields");
	}
}
