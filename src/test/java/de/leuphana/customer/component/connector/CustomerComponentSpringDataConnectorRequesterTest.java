package de.leuphana.customer.component.connector;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import de.leuphana.article.component.structure.Article;
import de.leuphana.article.component.structure.ArticleDescription;
import de.leuphana.customer.component.structure.Cart;
import de.leuphana.customer.component.structure.CartItem;
import de.leuphana.customer.component.structure.Customer;
import de.leuphana.customer.connector.CustomerComponentSpringDataConnectorRequester;
import de.leuphana.order.component.structure.Order;
import de.leuphana.order.component.structure.OrderPosition;

public class CustomerComponentSpringDataConnectorRequesterTest {

	private CustomerComponentSpringDataConnectorRequester customerComponentSpringDataConnectorRequester;

	@Before
	public void setUp() throws Exception {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("CustomerApplicationContext-SpringData-Connector.xml");
		
		customerComponentSpringDataConnectorRequester = (CustomerComponentSpringDataConnectorRequester) applicationContext.getBean("customerConnectorSpringDataJpa");
		
		//Object initialization
		ArticleDescription articleDescription = new ArticleDescription();
		Article article = new Article();
		Cart cart = new Cart ();
		Customer customer = new Customer();
		Order order = new Order();
		OrderPosition orderPosition = new OrderPosition();

		articleDescription.setAuthor("Egon");
		articleDescription.setText("Toller Article!!!");

		article.setName("Hut");
		article.setManufactor("Gut");
		article.setPrice(12.45f);
		article.setArticleDescription(articleDescription);
		
		
		cart.addCartItem(article, 1);
		
		orderPosition.setArticle(article);
		order.addOrderPosition(orderPosition);
		
		customer.setName("Hugo");
		customer.setCart(cart);
		customer.addOrder(order);
		customerComponentSpringDataConnectorRequester.insertCustomer(customer);
	}
		
	@After
	public void tearDown() throws Exception {
	}

	@Test
	@Transactional
	public void canCustomerBeFetched() {
		Assert.assertNotNull("CustomerEntity", customerComponentSpringDataConnectorRequester.findByNameLike("Hugo"));
	}

}
