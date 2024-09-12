package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.entity.CustomerEntity;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
	public static final String DB_URL = "jdbc:mysql://localhost:3306/northwind";
	public static final String USER = "root";
	public static final String PASSWORD = "123123";

	@Override
	public List<CustomerEntity> findAll(String name) {
		List<CustomerEntity> result = new ArrayList<>();
		String sql = "select * from customers where ContactName like '%" + name + "%'";
		try (Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {
			while (rs.next()) {
				CustomerEntity customerEntity = new CustomerEntity();
				customerEntity.setCustomerID(rs.getInt("CustomerID"));
				customerEntity.setCustomerName(rs.getString("CustomerName"));
				customerEntity.setContactName(rs.getString("ContactName"));
				customerEntity.setAddress(rs.getString("Address"));
				result.add(customerEntity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
