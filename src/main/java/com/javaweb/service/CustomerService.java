package com.javaweb.service;

import java.util.List;

import com.javaweb.model.CustomerDTO;

public interface CustomerService {
	List<CustomerDTO> findAll(String name);
}
