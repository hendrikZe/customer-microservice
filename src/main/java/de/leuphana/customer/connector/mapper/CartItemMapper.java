package de.leuphana.customer.connector.mapper;

import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;

import de.leuphana.customer.component.structure.CartItem;
import de.leuphana.customer.connector.entity.CartItemEntity;

public class CartItemMapper {

	private static Mapper mapper;

	static {
		mapper = DozerBeanMapperSingletonWrapper.getInstance();
	}

	public static CartItem convert2CartItemEntity(CartItem cartItem) {
		return mapper.map(cartItem, CartItem.class);
	}

	public static CartItem convert2CartItem(CartItemEntity cartItemEntity) {
		return mapper.map(cartItemEntity, CartItem.class);
	}

}