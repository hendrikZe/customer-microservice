package de.leuphana.customer.connector;

import java.util.ArrayList;
import java.util.List;

import de.leuphana.customer.component.structure.Cart;
import de.leuphana.customer.component.structure.CartItem;
import de.leuphana.customer.component.structure.Customer;

public class CustomerRequestWrapper {

	private Cart cart;
	private List<CartItem> cartItems;
	private Customer customer;

	public CustomerRequestWrapper() {
		cartItems = new ArrayList<CartItem>();
	}
	
	public Cart getCart() {
		// TODO Auto-generated method stub
		return cart;
	}

	public List<CartItem> getCartItems() {
		// TODO Auto-generated method stub
		return cartItems;
	}

	public Customer getCustomer() {
		// TODO Auto-generated method stub
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
		
	}

	public void setCart(Cart cart) {
		this.cart = cart;
		
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
		
	}

}
