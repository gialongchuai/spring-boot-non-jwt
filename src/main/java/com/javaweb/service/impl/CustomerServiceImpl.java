package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.model.CustomerDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.entity.CustomerEntity;
import com.javaweb.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired 
	private CustomerRepository customerRepository;
	@Override
	public List<CustomerDTO> findAll(String name) {
		
		List<CustomerEntity> customerEntities = customerRepository.findAll(name);
		List<CustomerDTO> customerDTOs = new ArrayList<>();
		for(CustomerEntity x : customerEntities) {
			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setCustomerID(x.getCustomerID());
			customerDTO.setCustomerName(x.getCustomerName());
			customerDTO.setContactName(x.getContactName());
			customerDTO.setAddress(x.getAddress());
			customerDTOs.add(customerDTO);
		}
		return customerDTOs;
	}

}
