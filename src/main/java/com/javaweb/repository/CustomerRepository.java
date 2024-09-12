package com.javaweb.repository;

import java.util.List;

import com.javaweb.repository.entity.CustomerEntity;

public interface CustomerRepository {
	List<CustomerEntity> findAll(String name);
}
