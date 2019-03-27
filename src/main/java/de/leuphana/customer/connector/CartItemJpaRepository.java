package de.leuphana.customer.connector;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.leuphana.customer.connector.entity.CartItemEntity;
import de.leuphana.customer.connector.entity.CustomerEntity;


public interface CartItemJpaRepository extends JpaRepository<CartItemEntity, Integer> {


}
