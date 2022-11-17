package edu.sru.group7.restaurantmanager.billing;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.RestController;

import edu.sru.group7.restaurantmanager.repository.PaymentDetailsRepository;

//PaymentDetails code provided by Cody Kuntz from the WebShopping group project

/**
 * Controller for handling PaymentDetails in database
 */
@RestController
public class PaymentDetailsController {
	@PersistenceContext
	private EntityManager entityManager;
	private PaymentDetailsRepository paymentDetailsRepository;
	
	public PaymentDetailsController(PaymentDetailsRepository paymentDetailsRepository) {
		this.paymentDetailsRepository = paymentDetailsRepository;
	}
	
	/**
	 * Deletes the passed PaymentDetails from the database
	 * @param details details to delete
	 */
	public void deletePaymentDetails(PaymentDetails details) {
		PaymentDetails dbDetails = entityManager.find(PaymentDetails.class, details.getId());
		if (dbDetails != null)
			paymentDetailsRepository.deleteById(dbDetails.getId());
	}
}
