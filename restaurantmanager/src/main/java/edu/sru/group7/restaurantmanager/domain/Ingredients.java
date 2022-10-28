package edu.sru.group7.restaurantmanager.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * 'Ingredients' class that creates entity in database
 * @author Bradley Smith
 * 
 */
@Entity
public class Ingredients {

	@Id
	private long id;
	
	@ManyToOne
    @JoinColumn(name = "menu_id")
	private Menu menu;
	
	//list
	private Vector ingredient = new Vector<>();

	public Ingredients(long id, Menu menu, Vector ingredient) {
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

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Vector getIngredient() {
		return ingredient;
	}

	public void setIngredient(Vector ingredient) {
		this.ingredient = ingredient;
	}
	
	
}