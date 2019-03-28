package de.leuphana.customer.connector.mapper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;

import de.leuphana.customer.component.structure.CartItem;
import de.leuphana.customer.connector.entity.CartItemEntity;

public class CartItemMapper {

	private static Mapper mapper;

	static {
		mapper = DozerBeanMapperSingletonWrapper.getInstance();
	}

	public static CartItemEntity convert2CartItemEntity(CartItem cartItem) {
		return mapper.map(cartItem, CartItemEntity.class);
	}

	public static CartItem convert2CartItem(CartItemEntity cartItemEntity) {
		return mapper.map(cartItemEntity, CartItem.class);
	}

	public static List<CartItemEntity> convert2CartItemEntities(List<CartItem> cartItems) {
		List<CartItemEntity> entities = new ArrayList<>();
		for (CartItem cartItem : cartItems) {
			entities.add(mapper.map(cartItem, CartItemEntity.class));
		}
		return entities;
	}

}