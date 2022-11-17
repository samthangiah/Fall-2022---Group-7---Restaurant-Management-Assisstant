package edu.sru.group7.restaurantmanager.billing;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.RestController;

import edu.sru.group7.restaurantmanager.repository.PaypalRepository;

//Paypal details code provided by Cody Kuntz from the WebShopping group project

/**
 * Controller for handling Paypal in database
 */
@RestController
public class PaypalController {
	@PersistenceContext
	private EntityManager entityManager;
	private PaypalRepository paypalRepository;
	
	PaypalController(PaypalRepository paypalRepository) {
		this.paypalRepository = paypalRepository;
	}
	
	/**
	 * Deletes the passed PaymentDetails from the database
	 * @param details details to delete
	 */
	public void deletePaypalDetails(Paypal details) {
		Paypal dbDetails = entityManager.find(Paypal.class, details.getId());
		if (dbDetails != null)
			paypalRepository.deleteById(dbDetails.getId());
	}
}
