package de.leuphana.customer.component.structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.leuphana.order.component.structure.Order;

public class Customer {

	//intrinsic attributes
	private Integer customerId;
	private String name;
	private String address;
	private int cartId;
	private List<Integer> orderIds;

	public Customer() {
		orderIds = new ArrayList<Integer>();
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public int getCart() {
		return cartId;
	}
	
	public void setCart(int cartId) {
		this.cartId = cartId;
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
	
	public void addOrder(Integer orderId) {
		orderIds.add(orderId);
	}
	
	public void removeOderId(Integer orderId) {
		orderIds.remove(orderId);
	}
	
	public List<Integer> getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(List<Integer> orderIds) {
		this.orderIds = orderIds;
	}

}