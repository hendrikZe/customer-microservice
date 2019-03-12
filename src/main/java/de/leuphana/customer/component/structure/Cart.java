package de.leuphana.customer.component.structure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.leuphana.article.component.structure.Article;

public class Cart {

	private List<CartItem> cartItems;

	private int numberOfArticles;

	public Cart() {
		cartItems = new ArrayList<CartItem>();
		numberOfArticles = 0;
	}

	public void addCartItem(Article article, int quantity) {
		Integer articleId = article.getArticleId();
		CartItem cartItem;
		if (cartItems.contains(articleId)) {
			cartItem = cartItems.get(articleId);
			cartItem.incrementQuantity();
		} else {
			cartItem = new CartItem();
			cartItem.setArticle(article);
			cartItem.setQuantity(quantity);
			cartItems.add(cartItem);
		}
		numberOfArticles++;
	}

	public void deleteCartItem(int articleId) {
		for (CartItem cartItem : cartItems) {
			if (cartItem.getArticle().getArticleId() == (articleId)) {
				cartItems.remove(cartItem.getCartItemId());
				break;
			}
		}
	}

	public void decrementArticleQuantity(Integer articleId) {
		if (cartItems.contains(articleId)) {
			CartItem cartItem = (CartItem) cartItems.get(articleId);
			cartItem.decrementQuantity();

			if (cartItem.getQuantity() <= 0)
				cartItems.remove(articleId);

			numberOfArticles--;
		}
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}
	
	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	

	public int getNumberOfArticles() {
		return numberOfArticles;
	}

	public double getTotalPrice() {
		double totalPrice = 0.0;

		Article article;
		for (CartItem cartItem : getCartItems()) {
			article = cartItem.getArticle();

			totalPrice += cartItem.getQuantity() * article.getPrice();
		}

		return totalPrice;
	}

}