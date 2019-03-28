package de.leuphana.customer.connector.mapper;

import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;

import de.leuphana.customer.component.structure.Cart;
import de.leuphana.customer.connector.entity.CartEntity;

public class CartMapper {

	private static Mapper mapper;

	static {
		mapper = DozerBeanMapperSingletonWrapper.getInstance();
	}

	public static CartEntity convert2CartEntity(Cart cart) {
		return mapper.map(cart, CartEntity.class);
	}

	public static Cart convert2Cart(CartEntity cartEntity) {
		return mapper.map(cartEntity, Cart.class);
	}

}