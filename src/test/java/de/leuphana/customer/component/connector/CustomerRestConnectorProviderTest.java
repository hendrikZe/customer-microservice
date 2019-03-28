package de.leuphana.customer.component.connector;

import java.util.Arrays;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import de.leuphana.customer.component.behaviour.CustomerService;
import de.leuphana.customer.component.structure.Cart;
import de.leuphana.customer.component.structure.CartItem;
import de.leuphana.customer.component.structure.Customer;
import de.leuphana.customer.connector.CustomerRequestWrapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { CustomerService.class }, webEnvironment = WebEnvironment.DEFINED_PORT)
public class CustomerRestConnectorProviderTest {
	private static final String API_ROOT_CUSTOMER = "http://localhost:8086/api/customers";
	private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
	private int customerId;
	private int createCustomerAsUri(CustomerRequestWrapper customerRequestWrapper) {
		Response response = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(customerRequestWrapper).post(API_ROOT_CUSTOMER);
		logger.info("response :" + response.getStatusLine());
		logger.info(response.jsonPath().prettyPrint());
		return response.jsonPath().getInt("customerId");
	}
	@Before
	public void setUp() {
		//Object initialization
				Cart cart = new Cart ();
				Customer customer = new Customer();		
				CartItem cartItem = new CartItem();
				cartItem.setQuantity(3);
				cartItem.setArticleId(13);
				
			//	orderPosition.setArticle(article);
				//order.addOrderPosition(orderPosition);
				
				customer.setName("Hugo");
				customer.addOrderId(11);
				CustomerRequestWrapper customerRequestWrapper = new CustomerRequestWrapper();
				customerRequestWrapper.setCustomer(customer);
				customerRequestWrapper.setCart(cart);
				customerRequestWrapper.setCartItems(Arrays.asList(cartItem));
		customerId = createCustomerAsUri(customerRequestWrapper);
	}

	@Test
	public void test() {
		
		Response response = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(customerId).get(API_ROOT_CUSTOMER + "/id/" + customerId);
		Assert.assertNotNull(response.jsonPath().getInt("customerId"));
	}
	@After
	public void tearDown() {
		RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(customerId).delete(API_ROOT_CUSTOMER + "/id/" + customerId);

	}

}
