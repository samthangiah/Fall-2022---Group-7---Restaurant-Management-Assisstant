package edu.sru.group7.restaurantmanager.repository;

import org.springframework.data.repository.CrudRepository;

import edu.sru.group7.restaurantmanager.billing.Paypal;

//Paypal details code provided by Cody Kuntz from the WebShopping group project

/**
 * Paypal Repository
 */
public interface PaypalRepository extends CrudRepository<Paypal, Long> {

}
