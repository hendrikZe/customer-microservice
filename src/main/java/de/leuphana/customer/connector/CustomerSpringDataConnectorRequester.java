package de.leuphana.customer.connector;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.leuphana.customer.component.structure.Cart;
import de.leuphana.customer.component.structure.CartItem;
import de.leuphana.customer.component.structure.Customer;
import de.leuphana.customer.connector.entity.CartEntity;
import de.leuphana.customer.connector.entity.CartItemEntity;
import de.leuphana.customer.connector.entity.CustomerEntity;
import de.leuphana.customer.connector.mapper.CartItemMapper;
import de.leuphana.customer.connector.mapper.CartMapper;
import de.leuphana.customer.connector.mapper.CustomerMapper;


@Service
public class CustomerSpringDataConnectorRequester {
	
	
	@Autowired
	private CustomerJpaRepository customerJpaRepository;
	@Autowired
	private CartJpaRepository cartJpaRepository;
	@Autowired
	private CartItemJpaRepository cartItemJpaRepository;

	public Customer findCustomerById(Integer id) {
		CustomerEntity customerEntity = customerJpaRepository.findById(id).get();

		return CustomerMapper.convert2Customer(customerEntity);
	}

	@Transactional
	public void insertCustomer(Customer customer) {
		customerJpaRepository.save(CustomerMapper.convert2CustomerEntity(customer));
	}

	@Transactional
	public List<Customer> findByNameLike(String name) {
		List<CustomerEntity> customerEntityList = customerJpaRepository.findByNameLike(name);
		
		// TODO ArticleMapper um mapping-Methoden f�r Beh�lter erweitern
		List<Customer> customers = new ArrayList<>();
		for (CustomerEntity customerEntity : customerEntityList) {
			customers.add(CustomerMapper.convert2Customer(customerEntity));
		}
		
		return customers;
	}

	public Cart insertOrUpdateCart(Cart cart, List<CartItem> cartItems) {
		List<CartItemEntity> cartItemEntites = 	cartItemJpaRepository.saveAll(CartItemMapper.convert2CartItemEntities(cartItems));
		for (CartItemEntity cartItemEntity : cartItemEntites) {
			cart.addCartItemId(cartItemEntity.getCartItemId());
		}
		return CartMapper.convert2Cart(cartJpaRepository.save(CartMapper.convert2CartEntity(cart)));
	}

	public Cart findCartById(int id) {
		return CartMapper.convert2Cart(cartJpaRepository.findById(id).get());
	}

	public void deleteCart(Cart cart, List<CartItem> cartItems) {
		cartItemJpaRepository.deleteAll(CartItemMapper.convert2CartItemEntities(cartItems));
		cartJpaRepository.delete(CartMapper.convert2CartEntity(cart));
		
	}

	public void deleteCartById(int cartId) {
		List<Integer> cartItemIds = cartJpaRepository.findById(cartId).get().getCartItemEntityIds();
		for (Integer integer : cartItemIds) {
			cartItemJpaRepository.deleteById(integer);
		}
		cartJpaRepository.deleteById(cartId);
	}

	public CartItem findCartItemById(Integer cartItemId) {
		return CartItemMapper.convert2CartItem(cartItemJpaRepository.findById(cartItemId).get());
	}

	public Cart insertOrUpdateCart(Cart cart) {
		return CartMapper.convert2Cart(cartJpaRepository.save(CartMapper.convert2CartEntity(cart)));
	}

	public CartItem insertOrUpdateCartItem(CartItem cartItem) {
		return CartItemMapper.convert2CartItem(cartItemJpaRepository.save(CartItemMapper.convert2CartItemEntity(cartItem)));
	}

	public void removeCartItem(CartItem cartItem) {
		cartItemJpaRepository.deleteById(cartItem.getCartItemId());		
	}

	public Customer insertCustomer(Customer customer, Cart cart, List<CartItem> cartItems) {
		List<CartItemEntity> cartItemEntites = 	cartItemJpaRepository.saveAll(CartItemMapper.convert2CartItemEntities(cartItems));
		for (CartItemEntity cartItemEntity : cartItemEntites) {
			cart.addCartItemId(cartItemEntity.getCartItemId());
		}
		CartEntity cartEntity=cartJpaRepository.save(CartMapper.convert2CartEntity(cart));
		customer.setCartId(cartEntity.getCartId());
		return CustomerMapper.convert2Customer(customerJpaRepository.save(CustomerMapper.convert2CustomerEntity(customer)));
			
	}

	public void deleteCustomer(Customer customer) {
		CartEntity cartEntity = cartJpaRepository.findById(customer.getCartId()).get();
		for (Integer id : cartEntity.getCartItemEntityIds()) {
			cartItemJpaRepository.deleteById(id);
		}
		cartJpaRepository.deleteById(customer.getCartId());
		customerJpaRepository.deleteById(customer.getCustomerId());
		
	}

	public List<Customer> findAll() {
		List<Customer> result = new ArrayList<Customer>();
		Iterable<CustomerEntity> customerEntities = customerJpaRepository.findAll();
		for (CustomerEntity customerEntity : customerEntities) {
			result.add(CustomerMapper.convert2Customer(customerEntity));
		}
		return result;
	}

}
