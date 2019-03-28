package de.leuphana.customer.component.behaviour;

import java.util.Arrays;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import de.leuphana.customer.component.structure.Cart;
import de.leuphana.customer.component.structure.CartItem;
import de.leuphana.customer.component.structure.Customer;
import de.leuphana.customer.connector.CustomerSpringDataConnectorRequester;
@Rollback(false)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { CustomerService.class }, webEnvironment = WebEnvironment.DEFINED_PORT)
public class CustomerServiceTest {

	@Autowired
	private CustomerService customerService;
	private Customer customer;
	@Before
	public void setUp() throws Exception {
			
		//Object initialization
		Cart cart = new Cart ();
		customer = new Customer();		
		CartItem cartItem = new CartItem();
		cartItem.setQuantity(3);
		cartItem.setArticleId(14);
		
	//	orderPosition.setArticle(article);
		//order.addOrderPosition(orderPosition);
		
		customer.setName("Hugo");
		customer.addOrderId(11);
		customer = customerService.insertCustomer(customer,cart,Arrays.asList(cartItem));
	}
	@After
	public void tearDown() throws Exception {
		//customerComponentSpringDataConnectorRequester.deleteCustomer(customer);
	}

	@Test
	@Transactional
	public void canCustomerBeFetched() {
		Assert.assertNotNull("CustomerEntity", customerService.getCustomerById(customer.getCustomerId()));
	}
}
