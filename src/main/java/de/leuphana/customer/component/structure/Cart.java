package de.leuphana.customer.component.structure;

import java.util.ArrayList;
import java.util.List;

public class Cart {

	private List<Integer> cartItemIds;

	private int numberOfArticles;

	public Cart() {
		cartItemIds = new ArrayList<Integer>();
		numberOfArticles = 0;
	}

	

	public List<Integer> getCartItems() {
		return cartItemIds;
	}
	
	public void setCartItems(List<Integer> cartItemIds) {
		this.cartItemIds = cartItemIds;
	}
	

	public int getNumberOfArticles() {
		return numberOfArticles;
	}
	public void setNumberOfArticles(int number) {
		this.numberOfArticles = number;
	}


}