package de.leuphana.customer.component.connector;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import de.leuphana.customer.component.behaviour.CustomerService;
import de.leuphana.customer.connector.OrderRestConnectorRequester;
import de.leuphana.order.component.structure.Order;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { CustomerService.class }, webEnvironment = WebEnvironment.DEFINED_PORT)
public class OrderRestConnectorRequesterTest {

	@Autowired 
	OrderRestConnectorRequester orderRestConnectorRequester;
	@Test
	public void test() {
		Order order = orderRestConnectorRequester.getOrderById(3);
		System.out.println(order.getOrderPositionIds().get(0));
		Assert.assertNotNull(orderRestConnectorRequester.getOrderById(3));
	}


}
