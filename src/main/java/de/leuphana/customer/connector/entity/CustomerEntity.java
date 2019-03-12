package de.leuphana.customer.connector.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import de.leuphana.customer.component.structure.Cart;

@Entity
public class CustomerEntity {

	private static Integer lastGeneratedCustomerId = null;

	//intrinsic attributes
	private Integer customerId;
	private String name;
	private String address;
	//private Cart cart; <- verschoben als relationales attribut, weil 1 Kunde = 1 Cart
	//private Map<Integer, Order> orders;
	
	//realitonal attributes
	private CartEntity cartEntity;
	
	public CustomerEntity() {
	}
	
	@Id
	@GeneratedValue
	public Integer getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn()
	public CartEntity getCartEntity() {
		return cartEntity;
	}
	
	public void setCartEntity(CartEntity cartEntity) {
		this.cartEntity = cartEntity;
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

