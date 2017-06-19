package com.example.service;

import com.example.model.User;

public interface UserService {
	User findUserByEmail(String email);
	void saveUser(User user);
}