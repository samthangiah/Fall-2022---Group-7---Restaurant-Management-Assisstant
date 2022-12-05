package edu.sru.group7.restaurantmanager.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Vector;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test class for Ingredients class
 */
@SpringBootTest
public class IngredientsTest {
	
	@Test
	public void gettersAndSettersTests() {
		Ingredients ingredient = new Ingredients();
		
		ingredient.setId(1);
		assertEquals((long) 1, ingredient.getId(), "ingredient getId should return Id");
		ingredient.setId(2);
		assertEquals((long) 2, ingredient.getId(), "ingredient setId should set Id property");
		
		Menu menu = new Menu();
		Vector<String> ingredients = new Vector<String>();
		
		ingredient.setMenu(menu);
		assertEquals(menu, ingredient.getMenu(), "ingredient getMenu should return menu");
		ingredient.setMenu(null);
		assertNull(ingredient.getMenu(), "ingredient setMenu should set menu property");
		
		ingredient.setIngredient(ingredients);
		assertEquals(ingredients, ingredient.getIngredient(), "ingredient getIngredient should return ingredients");
		ingredient.setIngredient(null);
		assertNull(ingredient.getIngredient(), "ingredient setIngredient should set ingredient property");
	}
}
