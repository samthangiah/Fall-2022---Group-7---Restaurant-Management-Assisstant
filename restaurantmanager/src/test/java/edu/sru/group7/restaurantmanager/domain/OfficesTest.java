package edu.sru.group7.restaurantmanager.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test class for Offices class
 */
@SpringBootTest
public class OfficesTest {
	
	@Test
	public void gettersAndSettersTests() {
		Offices office = new Offices();
		
		office.setId(1);
		assertEquals((long) 1, office.getId(), "office getId should return Id");
		office.setId(2);
		assertEquals((long) 2, office.getId(), "office setId should set Id property");
		
		office.setAddress("test");
		assertEquals("test", office.getAddress(), "office getAddress should return address");
		office.setAddress("address");
		assertEquals("address", office.getAddress(), "office setAddress should set address property");
		
		office.setState("test");
		assertEquals("test", office.getState(), "office getState should return state");
		office.setState("PA");
		assertEquals("PA", office.getState(), "office setState should set state property");
		
		office.setZipcode("test");
		assertEquals("test", office.getZipcode(), "office getZipcode should return zipcode");
		office.setZipcode("12345");
		assertEquals("12345", office.getZipcode(), "office setZipcode should set zipcode property");
		
		office.setCity("test");
		assertEquals("test", office.getCity(), "office getCity should return city");
		office.setCity("slippery rock");
		assertEquals("slippery rock", office.getCity(), "office setCity should set city property");
		
		Admins admin = new Admins();
		List<Admins> admins = new ArrayList<Admins>();
		admins.add(admin);
		
		office.setAdmin(admins);
		assertEquals(admins, office.getAdmin(), "office getAdmin should return admins");
		office.setAdmin(null);
		assertNull(office.getAdmin(), "office setAdmin should set admin property");
		
		assertEquals("Office ID: 2 Address: address", office.toString(), "office toString should return string id and address");
	}
}
