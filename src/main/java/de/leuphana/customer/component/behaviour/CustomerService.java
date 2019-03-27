package de.leuphana.customer.component.behaviour;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;

import de.leuphana.article.component.structure.Article;
import de.leuphana.customer.component.structure.Cart;
import de.leuphana.customer.component.structure.CartItem;
import de.leuphana.customer.connector.CustomerSpringDataConnectorRequester;
import de.leuphana.customer.connector.entity.CartEntity;
import de.leuphana.order.component.behaviour.OrderService;
import de.leuphana.order.connector.ArticleRestConnectorRequester;
import de.leuphana.order.connector.OrderRestConnectorProvider;

@EnableJpaRepositories("de.leuphana.order.connector")
@EntityScan("de.leuphana.order.connector.entity")
@ComponentScan(basePackageClasses = OrderRestConnectorProvider.class)
@Controller
@EnableFeignClients(basePackages = "de.leuphana.order.connector")
@SpringBootApplication
public class CustomerService {
	@Autowired
	private CustomerSpringDataConnectorRequester customerSpringDataConnectorRequester;
	@Autowired
	private ArticleRestConnectorRequester articleRestConnectorRequester;
	public static void main(String[] args) {
        SpringApplication.run(OrderService.class, args);
    }
	@Transactional
    public CartEntity insertCart(Cart cart, List<CartItem> cartItems) {
    	return customerSpringDataConnectorRequester.insertCart(cart, cartItems);
    }
	
	public Cart getCartById(int id) {
		return customerSpringDataConnectorRequester.findById(id);
	}
	public Article getArticleById(int id) {
		return articleRestConnectorRequester.getArticleById(id);
	}
	public void deleteCart(Cart cart, List<CartItem> cartItems) {
		customerSpringDataConnectorRequester.deleteCart(cart, cartItems);		
	}
	public void deleteCartById(int cartId) {
		customerSpringDataConnectorRequester.deleteCartById(cartId);		
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
}
