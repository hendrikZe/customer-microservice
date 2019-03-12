package de.leuphana.customer.connector.mapper;

import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;

import de.leuphana.customer.component.structure.Customer;
import de.leuphana.customer.connector.entity.CustomerEntity;

public class CustomerMapper {

	private static Mapper mapper;

	static {
		mapper = DozerBeanMapperSingletonWrapper.getInstance();
	}

	public static CustomerEntity convert2CustomerEntity(Customer customer) {
		return mapper.map(customer, CustomerEntity.class);
	}

	public static Customer convert2Customer(CustomerEntity customerEntity) {
		return mapper.map(customerEntity, Customer.class);
	}

}