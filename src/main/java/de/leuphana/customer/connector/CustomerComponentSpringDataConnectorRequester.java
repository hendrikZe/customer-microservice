package de.leuphana.customer.connector;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import de.leuphana.customer.component.structure.Customer;
import de.leuphana.customer.connector.entity.CustomerEntity;
import de.leuphana.customer.connector.mapper.CustomerMapper;



public class CustomerComponentSpringDataConnectorRequester {
	
	
	@Autowired
	private CustomerJpaRepository customerJpaRepository;

	public Customer findCustomerById(Integer id) {
		CustomerEntity customerEntity = customerJpaRepository.findOne(id);

		return CustomerMapper.convert2Customer(customerEntity);
	}

	@Transactional
	public void insertCustomer(Customer customer) {
		customerJpaRepository.save(CustomerMapper.convert2CustomerEntity(customer));
	}

	@Transactional
	public List<Customer> findByNameLike(String name) {
		List<CustomerEntity> customerEntityList = customerJpaRepository.findByNameLike(name);
		
		// TODO ArticleMapper um mapping-Methoden für Behälter erweitern
		List<Customer> customers = new ArrayList<>();
		for (CustomerEntity customerEntity : customerEntityList) {
			customers.add(CustomerMapper.convert2Customer(customerEntity));
		}
		
		return customers;
	}
}
