package com.javaweb.repository;

import java.util.List;

import com.javaweb.repository.entity.UserEntity;

public interface UserRepository {
	List<UserEntity> findAll();
}
