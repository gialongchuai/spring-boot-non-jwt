package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.javaweb.repository.UserRepository;
import com.javaweb.repository.entity.UserEntity;

@Service
public class UserRepositoryImpl implements UserRepository {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "123123";

	@Override
	public List<UserEntity> findAll() {
		String sql = "Select * from user";
		List<UserEntity> userEntities = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
				Statement statement = con.createStatement();
				ResultSet resultSet = statement.executeQuery(sql)) {
			while (resultSet.next()) {
				UserEntity userEntity = new UserEntity();
				userEntity.setUsername(resultSet.getString("username"));
				userEntity.setPassword(resultSet.getInt("password"));
				userEntity.setFullname(resultSet.getString("fullname"));
				userEntities.add(userEntity);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return userEntities;
	}

}
