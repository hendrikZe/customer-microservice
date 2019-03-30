package de.leuphana.customer.component.behaviour;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;

import de.leuphana.article.component.structure.Article;
import de.leuphana.customer.component.structure.Cart;
import de.leuphana.customer.component.structure.CartItem;
import de.leuphana.customer.component.structure.Customer;
import de.leuphana.customer.connector.ArticleRestConnectorRequester;
import de.leuphana.customer.connector.CustomerRestConnectorProvider;
import de.leuphana.customer.connector.CustomerSpringDataConnectorRequester;
import de.leuphana.customer.connector.OrderRestConnectorRequester;
import de.leuphana.customer.connector.mapper.CartItemMapper;
import de.leuphana.order.component.structure.Order;

@EnableJpaRepositories("de.leuphana.customer.connector")
@EntityScan("de.leuphana.customer.connector.entity")
@ComponentScan(basePackageClasses = CustomerRestConnectorProvider.class)
@Controller
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "de.leuphana.customer.connector")
@SpringBootApplication
public class CustomerService {
	@Autowired
	private CustomerSpringDataConnectorRequester customerSpringDataConnectorRequester;
	@Autowired
	private ArticleRestConnectorRequester articleRestConnectorRequester;
	@Autowired
	private OrderRestConnectorRequester orderRestConnectorRequester;

	public static void main(String[] args) {
		SpringApplication.run(CustomerService.class, args);
	}

	@Transactional
	public Cart insertCart(Cart cart, List<CartItem> cartItems) {
		return customerSpringDataConnectorRequester.insertOrUpdateCart(cart, cartItems);
	}

	public Cart getCartById(int id) {
		return customerSpringDataConnectorRequester.findCartById(id);
	}

	public Article getArticleById(int id) {
		return articleRestConnectorRequester.getArticleById(id);
	}

	public Order getOrderById(int id) {
		return orderRestConnectorRequester.getOrderById(id);
	}

	public void deleteCartById(int cartId) {
		customerSpringDataConnectorRequester.deleteCartById(cartId);
	}

	public void addCartItem(int cartId, int articleId, int quantity) {
		// Cart selektieren, dann CartItems selektieren,
		// Dann gucken ob article vorhanden, wenn ja, IncrementQuantitiy und Update
		// Sonst insert
		Cart cart = customerSpringDataConnectorRequester.findCartById(cartId);
		Boolean cartItemExists = false;
		for (Integer cartItemId : cart.getCartItemIds()) {
			CartItem cartItem = customerSpringDataConnectorRequester.findCartItemById(cartItemId);
			if (cartItem.getArticleId() == articleId) {
				cartItem.increaseQuantity(quantity);
				customerSpringDataConnectorRequester.insertOrUpdateCartItem(cartItem);
				cart.setNumberOfArticles(cart.getNumberOfArticles() + quantity);
				cartItemExists = true;
			}
		}
		if (!cartItemExists) {
			CartItem cartItem = new CartItem();
			cartItem.setArticleId(articleId);
			cartItem.setQuantity(quantity);
			int cartItemId = customerSpringDataConnectorRequester.insertOrUpdateCartItem(cartItem).getCartItemId();
			cart.addCartItemId(cartItemId);
			cart.setNumberOfArticles(cart.getNumberOfArticles() + quantity);
		}
		customerSpringDataConnectorRequester.insertOrUpdateCart(cart);
	}

	public void deleteCartItem(int cartId, int articleId) {
		Cart cart = customerSpringDataConnectorRequester.findCartById(cartId);
		for (Integer cartItemId : cart.getCartItemIds()) {
			CartItem cartItem = customerSpringDataConnectorRequester.findCartItemById(cartItemId);
			if (cartItem.getArticleId() == articleId) {
				cart.removeCartItemId(cartItem.getCartItemId());
				cart.setNumberOfArticles(cart.getNumberOfArticles() - cartItem.getQuantity());
				customerSpringDataConnectorRequester.removeCartItem(cartItem);
				customerSpringDataConnectorRequester.insertOrUpdateCart(cart);
				break;
			}
		}
	}

	public CartItem getCartItemById(int cartItemId) {
		return customerSpringDataConnectorRequester.findCartItemById(cartItemId);
	}

	public void decrementArticleQuantity(int cartId, Integer articleId, int quantity) {
		Cart cart = customerSpringDataConnectorRequester.findCartById(cartId);
		for (Integer cartItemId : cart.getCartItemIds()) {
			CartItem cartItem = customerSpringDataConnectorRequester.findCartItemById(cartItemId);
			if (cartItem.getArticleId() == articleId) {
				cartItem.decreaseQuantity(quantity);
				if (cartItem.getQuantity() <= 0) {
					customerSpringDataConnectorRequester.removeCartItem(cartItem);
					cart.removeCartItemId(cartItemId);
				} else {
					customerSpringDataConnectorRequester.insertOrUpdateCartItem(cartItem);
				}
				cart.setNumberOfArticles(cart.getNumberOfArticles() - quantity);
				customerSpringDataConnectorRequester.insertOrUpdateCart(cart);
				break;
			}
		}
	}

	public double getTotalPrice(int cartId) {
		double totalPrice = 0.0;
		Cart cart = customerSpringDataConnectorRequester.findCartById(cartId);
		for (Integer cartItemId : cart.getCartItemIds()) {
			CartItem cartItem = customerSpringDataConnectorRequester.findCartItemById(cartItemId);
			totalPrice += cartItem.getQuantity()
					* articleRestConnectorRequester.getArticleById(cartItem.getArticleId()).getPrice();
		}
		return totalPrice;
	}

	public Customer insertCustomer(Customer customer, Cart cart, List<CartItem> cartItems) {
		return customerSpringDataConnectorRequester.insertCustomer(customer, cart, cartItems);
	}

	public Customer getCustomerById(int customerId) {
		return customerSpringDataConnectorRequester.findCustomerById(customerId);
	}

	public void deleteCustomerById(int customerId) {
		customerSpringDataConnectorRequester
				.deleteCustomer(customerSpringDataConnectorRequester.findCustomerById(customerId));
	}

	public List<Customer> getAllCustomers() {
		return customerSpringDataConnectorRequester.findAll();
	}
}
