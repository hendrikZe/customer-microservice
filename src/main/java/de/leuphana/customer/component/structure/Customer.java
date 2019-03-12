package de.leuphana.customer.component.structure;

import java.util.HashMap;
import java.util.Map;

public class Customer {
	private static Integer lastGeneratedCustomerId;

	//intrinsic attributes
	private Integer customerId;
	private String name;
	private String address;
	private Cart cart;
	//private Map<Integer, Order> orders;
	
	static {
		lastGeneratedCustomerId = new Integer(1);
	}

	public Customer() {
		//orders = new HashMap<Integer, Order>();
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public Cart getCart() {
		return cart;
	}
	
	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	//public void addOrder(Order order) {
		//orders.put(order.getOrderId(), order);
	//}

}