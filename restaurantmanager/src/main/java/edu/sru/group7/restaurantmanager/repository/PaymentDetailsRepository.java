package edu.sru.group7.restaurantmanager.repository;

import org.springframework.data.repository.CrudRepository;

import edu.sru.group7.restaurantmanager.billing.PaymentDetails;

//PaymentDetails code provided by Cody Kuntz from the WebShopping group project

public interface PaymentDetailsRepository extends CrudRepository<PaymentDetails, Long> {

}
