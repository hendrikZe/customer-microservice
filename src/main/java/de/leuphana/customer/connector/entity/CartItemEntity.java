package de.leuphana.customer.connector.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import de.leuphana.article.component.structure.Article;
import de.leuphana.article.connector.entity.ArticleEntity;

@Entity
public class CartItemEntity {

	private int cartItemId;
	private int articleEntityId;
	private int quantity;
	
	public CartItemEntity() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue
	public int getCartItemId() {
		return cartItemId;
	}
	
	public void setCartItemId(int cartItemID) {
		this.cartItemId = cartItemID;
	}

	public int getArticleEntityId() {
		return articleEntityId;
	}
	
	public void setArticleEntityId(int articleEntityId) {
		this.articleEntityId = articleEntityId;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity (int quantity) {
		this.quantity = quantity;
	}

	public void incrementQuantity() {
		quantity++;
	}
	
	public void decrementQuantity() {
		quantity--;
	}

}