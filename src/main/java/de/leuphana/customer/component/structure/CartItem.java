package de.leuphana.customer.component.structure;

import de.leuphana.article.component.structure.Article;

public class CartItem {

	private int cartItemId;
	private Article article;
	private int quantity;

	public CartItem() {

	}
	
	public CartItem (Article article) {
		this.article = article;
	}

	public int getCartItemId() {
		return cartItemId;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void incrementQuantity() {
		quantity++;
	}

	public void decrementQuantity() {
		quantity--;
	}

}