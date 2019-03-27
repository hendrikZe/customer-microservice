package de.leuphana.customer.component.structure;

import de.leuphana.article.component.structure.Article;

public class CartItem {

	private int cartItemId;
	private int articleId;
	private int quantity;

	public CartItem() {

	}
	

	public int getCartItemId() {
		return cartItemId;
	}

	public int getArticle() {
		return articleId;
	}

	public void setArticle(int articleId) {
		this.articleId = articleId;
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