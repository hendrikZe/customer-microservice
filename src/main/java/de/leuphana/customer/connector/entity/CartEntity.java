package de.leuphana.customer.connector.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
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
	private List<CartItemEntity> cartItemEntities;
	private int numberOfArticles;

	public CartEntity() {
		cartItemEntities = new ArrayList<CartItemEntity>();
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

	public void addCartItem(ArticleEntity articleEntity) {
		Integer articleId = articleEntity.getArticleId();
		CartItemEntity cartItemEntity;
		if (cartItemEntities.contains(articleId)) {
			cartItemEntity = cartItemEntities.get(articleId);
			cartItemEntity.incrementQuantity();
		} else {
			cartItemEntity = new CartItemEntity();
			cartItemEntity.setArticleEntity(articleEntity);
			cartItemEntities.add(cartItemEntity);
		}
		numberOfArticles++;
	}

	public void deleteCartItem(int articleId) {
		for (CartItemEntity cartItem : cartItemEntities) {
			if (cartItem.getArticleEntity().getArticleId() == (articleId)) {
				cartItemEntities.remove(cartItem.getCartItemId());
				break;
			}
		}
	}

	public void decrementArticleQuantity(Integer articleId) {
		if (cartItemEntities.contains(articleId)) {
			CartItemEntity cartItem = (CartItemEntity) cartItemEntities.get(articleId);
			cartItem.decrementQuantity();

			if (cartItem.getQuantity() <= 0)
				cartItemEntities.remove(articleId);

			numberOfArticles--;
		}
	}

	@OneToMany(cascade = CascadeType.ALL)
	public List<CartItemEntity> getCartItemEntities() {
		return cartItemEntities;
	}

	public void setCartItemEntities(List<CartItemEntity> cartItemEntities) {
		this.cartItemEntities = cartItemEntities;
	}

	public int getNumberOfArticles() {
		return numberOfArticles;
	}
	
	public void setNumberOfArticles(int numberOfArticles) {
		this.numberOfArticles = numberOfArticles;
	}

//	public double getTotalPrice() {
//		double totalPrice = 0.0;
//
//		ArticleEntity article;
//		for (CartItemEntity cartItem : getCartItemEntities()) {
//			article = cartItem.getArticleEntity();
//
//			totalPrice += cartItem.getQuantity() * article.getPrice();
//		}
//
//		return totalPrice;
//	}

}
