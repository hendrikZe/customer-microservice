package de.leuphana.customer.connector;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.classic.Logger;
import de.leuphana.customer.component.behaviour.CustomerService;
import de.leuphana.customer.component.structure.Cart;
import de.leuphana.customer.component.structure.Customer;

@RestController
@RequestMapping(value = "/api/customers")
public class CustomerRestConnectorProvider {
	@Autowired
	private CustomerService customerService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Customer create(@RequestBody CustomerRequestWrapper customerRequestWrapper) {
		Customer customer = customerService.insertCustomer(customerRequestWrapper.getCustomer(),
				customerRequestWrapper.getCart(), customerRequestWrapper.getCartItems());
		System.out.println("CUSTOMER ID: " + customer.getCustomerId());
		return customer;
	}

	@GetMapping("/id/{customerId}")
	private Customer findById(@PathVariable int customerId) {
		return customerService.getCustomerById(customerId);
	}

	@DeleteMapping("/id/{customerId}")
	@ResponseStatus(HttpStatus.OK)
	private void deleteById(@PathVariable int customerId) {
		customerService.deleteCustomerById(customerId);
	}
	
	@GetMapping()
	private List<Customer> getCustomers() {
		return customerService.getAllCustomers();
	}
	@DeleteMapping("/cart/id/{cartId}/article/id/{articleId}")
		public void removeArticleFromCart(@PathVariable int cartId, @PathVariable int articleId){
		customerService.deleteCartItem(cartId, articleId);		
	}
	@GetMapping("/cart/id/{cartId}")
	public Cart getCartById(@PathVariable int cartId) {
		System.out.println("Will select Cart: ");
		return customerService.getCartById(cartId);
	}
}
