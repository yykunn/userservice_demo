package com.userservice.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userservice.demo.repository.UserRepo;
import com.userservice.demo.schema.generated.user_service_demo.tables.records.UserRecord;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	public UserRecord getUserById(int userId) {
		return userRepo.getUserById(userId);
	}

}
