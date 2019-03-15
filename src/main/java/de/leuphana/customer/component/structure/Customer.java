package de.leuphana.customer.component.structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.leuphana.order.component.structure.Order;

public class Customer {
	private static Integer lastGeneratedCustomerId;

	//intrinsic attributes
	private Integer customerId;
	private String name;
	private String address;
	private Cart cart;
	private List<Order> orders;

	static {
		lastGeneratedCustomerId = new Integer(1);
	}

	public Customer() {
		orders = new ArrayList<Order>();
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
	
	public void addOrder(Order order) {
		orders.add(order);
	}
	
	public void removeOder(Order order) {
		orders.remove(order);
	}
	
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}