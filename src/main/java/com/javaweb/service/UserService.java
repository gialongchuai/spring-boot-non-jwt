package com.javaweb.service;

import java.util.List;

import com.javaweb.model.UserDTO;

public interface UserService {
	List<UserDTO> findAll();
}
