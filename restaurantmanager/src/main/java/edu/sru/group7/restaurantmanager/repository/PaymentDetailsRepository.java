package edu.sru.group7.restaurantmanager.repository;

import org.springframework.data.repository.CrudRepository;

import edu.sru.group7.restaurantmanager.billing.PaymentDetails;

public interface PaymentDetailsRepository extends CrudRepository<PaymentDetails, Long> {

}
