package de.leuphana.customer.connector;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.leuphana.customer.connector.entity.CartEntity;
import de.leuphana.customer.connector.entity.CustomerEntity;


public interface CartJpaRepository extends JpaRepository<CartEntity, Integer> {


}
