package de.leuphana.customer.connector.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import de.leuphana.article.component.structure.Article;
import de.leuphana.article.connector.entity.ArticleEntity;
import de.leuphana.customer.component.structure.CartItem;

@Entity
public class CartEntity {

	private int cartId;
	private List<Integer> cartItemEntityIds;
	private int numberOfArticles;

	public CartEntity() {
		cartItemEntityIds = new ArrayList<Integer>();
		numberOfArticles = 0;
	}

	@Id
	@GeneratedValue
	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	@ElementCollection(targetClass=Integer.class)
	public List<Integer> getCartItemEntityIds() {
		return cartItemEntityIds;
	}

	public void setCartItemEntityIds(List<Integer> cartItemEntitieIds) {
		this.cartItemEntityIds = cartItemEntitieIds;
	}

	public int getNumberOfArticles() {
		return numberOfArticles;
	}
	
	public void setNumberOfArticles(int numberOfArticles) {
		this.numberOfArticles = numberOfArticles;
	}


}
