package edu.sru.group7.restaurantmanager.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.sru.group7.restaurantmanager.domain.Ingredients;
import edu.sru.group7.restaurantmanager.domain.Menu;


/**
 * Test class for Ingredients repository class
 */
@SpringBootTest
public class IngredientsRepositoryTest {
	
	@Autowired
	IngredientsRepository ingredientsRepo;
		
	@Autowired
	MenuRepository menuRepo;
	
	@Test
	public void findByMenuItemTest() {
		Menu menu = new Menu();
		menu.setId(1);
		Ingredients ingredient = new Ingredients();
		ingredient.setMenu(menu);
		menuRepo.save(menu);
		ingredientsRepo.save(ingredient);
		
		assertNotNull(ingredientsRepo.findByMenuItem(1), "ingredientsRepo should not return null");
	}
}