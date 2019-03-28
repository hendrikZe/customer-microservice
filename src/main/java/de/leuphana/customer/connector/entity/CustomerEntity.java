package de.leuphana.customer.connector.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import de.leuphana.customer.component.structure.Cart;
import de.leuphana.order.connector.entity.OrderEntity;

@Entity
public class CustomerEntity {


	//intrinsic attributes
	private Integer customerId;
	private String name;
	private String address;
	private int cartEntityId; 
	private List<Integer> orderEntityIds;
	
	
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
	

	public int getCartEntityId() {
		return cartEntityId;
	}
	
	public void setCartEntityId(int cartEntity) {
		this.cartEntityId = cartEntity;
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
	
	
	public void addOrderEntityId(int orderEntityId) {
		orderEntityIds.add(orderEntityId);
	}

	@ElementCollection(targetClass=Integer.class)
	public List<Integer> getOrderEntityIds() {
		return orderEntityIds;
	}

	public void setOrderEntityIds(List<Integer> orderEntitieIds) {
		this.orderEntityIds = orderEntitieIds;
	}


}

