package edu.sru.group7.restaurantmanager.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * POJO that holds all the current Cart Menu items for a Customer
 */
@Entity
public class CartItems {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

	@ManyToOne
	@JoinColumn(name="menu_id")
	private Menu menu_id;

	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customers customer_id;

	private Integer quantity;
	
	/**
	 * CartItems parameter constructor
	 * @param menu_id
	 * @param customer_id
	 * @param quantity
	 */
	public CartItems(Menu menu_id, Customers customer_id, Integer quantity) {
		super();
		this.menu_id = menu_id;
		this.customer_id = customer_id;
		this.quantity = quantity;
	}

	/**
	 * CartItems Default Constructor
	 */
	public CartItems() {
		super();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * @return Menu object belonging to the instance of CartItems
	 */
	public Menu getMenu_id() {
		return menu_id;
	}

	/**
	 * @param menu_id Sets Menu Object for an instance of CartItems
	 */
	public void setMenu_id(Menu menu_id) {
		this.menu_id = menu_id;
	}

	/**
	 * @return customer_id Customer Object that owns the current Cart
	 */
	public Customers getCustomer_id() {
		return customer_id;
	}
	
	/**
	 * @param customer_id Assigns Customer to instance of CartItems.
	 */
	public void setCustomer_id(Customers customer_id) {
		this.customer_id = customer_id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
