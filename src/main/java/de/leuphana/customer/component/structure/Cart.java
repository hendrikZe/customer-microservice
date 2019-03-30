package de.leuphana.customer.component.structure;

import java.util.ArrayList;
import java.util.List;

public class Cart {

	private Integer cartId;
	
	private List<Integer> cartItemIds;

	private int numberOfArticles;

	public Cart() {
		cartItemIds = new ArrayList<Integer>();
		numberOfArticles = 0;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public List<Integer> getCartItemIds() {
		return cartItemIds;
	}
	
	public void setCartItemIds(List<Integer> cartItemIds) {
		this.cartItemIds = cartItemIds;
	}
	

	public int getNumberOfArticles() {
		return numberOfArticles;
	}
	public void setNumberOfArticles(int number) {
		this.numberOfArticles = number;
	}



	public void addCartItemId(Integer cartItemId) {
		this.cartItemIds.add(cartItemId);
	}



	public void removeCartItemId(Integer cartItemId) {
		this.cartItemIds.remove(cartItemId);
		
	}


}