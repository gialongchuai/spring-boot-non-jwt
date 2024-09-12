package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaweb.model.UserDTO;
import com.javaweb.repository.UserRepository;
import com.javaweb.repository.entity.UserEntity;
import com.javaweb.service.UserService;

@Repository
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<UserDTO> findAll() {
		List<UserEntity> userEntities = userRepository.findAll();
		List<UserDTO> userDTOs = new ArrayList<>();
		for (UserEntity x : userEntities) {
			UserDTO userDTO = new UserDTO();
			userDTO.setUsername(x.getUsername());
			userDTO.setFullname(x.getFullname());
			userDTO.setPassword(x.getPassword());
			userDTOs.add(userDTO);
		}
		return userDTOs;
	}

}
