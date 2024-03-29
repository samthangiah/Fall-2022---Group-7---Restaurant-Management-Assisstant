package edu.sru.group7.restaurantmanager;
/*
 * https://themewagon.com/themes/elearning-free-bootstrap-5-css3-education-website-template/
 * All Security code (packages authentication and security; along with all of their classes, came from Amigoscode spring security tutorial on youtube
 * All Billing code (classes in the billing package, as well as the PaymentDetails_Form, Paypal_Form, PaymentDetailsRepository, and PaypalRepository) 
 * provided by Cody Kuntz from the WebShopping group project
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Class that starts springboot application
 * @author Bradley Smith
 * @author Ethan Pasman
 *
 */
@SpringBootApplication
@EnableScheduling
public class RestaurantmanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantmanagerApplication.class, args);
	}

}
