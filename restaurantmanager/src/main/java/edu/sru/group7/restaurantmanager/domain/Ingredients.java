package edu.sru.group7.restaurantmanager.domain;

import java.util.Vector;

import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * POJO that holds all the ingredients belonging to a Menu item
 * 
 */
@Entity
public class Ingredients {

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
    @JoinColumn(name = "menu_id")
	private Menu menu;
	
	private Vector<String> ingredient = new Vector<String>();

	public Ingredients(long id, Menu menu, Vector<String> ingredient) {
		super();
		this.id = id;
		this.menu = menu;
		this.ingredient = ingredient;
	}

	public Ingredients() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Menu getMenu() {
		return menu;
	}

	/**
	 * @param menu Assigns Menu object to Vector of ingredients
	 */
	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	/**
	 * 
	 * @return Vector of ingredients belonging to Menu object
	 */
	public Vector<String> getIngredient() {
		return ingredient;
	}

	public void setIngredient(Vector<String> ingredient) {
		this.ingredient = ingredient;
	}
}