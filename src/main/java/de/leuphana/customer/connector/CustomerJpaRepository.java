package de.leuphana.customer.connector;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.leuphana.customer.connector.entity.CustomerEntity;


public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Integer> {
	// Was? Wie heiﬂen Methodensignaturen
	
	// finde den Artikel der so ‰hnlich heiﬂt wie ...
	public List<CustomerEntity> findByNameLike(String name);
	
	// CoC == Convention over Configuration
	
	// SELECT article FROM Article WHERE name LIKE 

}
