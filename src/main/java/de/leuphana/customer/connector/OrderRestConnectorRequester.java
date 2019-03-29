package de.leuphana.customer.connector;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import de.leuphana.order.component.structure.Order;
@Service
@FeignClient(name="order-Microservice")
public interface OrderRestConnectorRequester {
	@GetMapping("/api/orders/id/{orderId}")
	public Order getOrderById(@PathVariable("orderId") int orderId);
}
