package com.nat.shoppinglist.service.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.nat.shoppinglist.dto.UserDto;

public interface UserService extends UserDetailsService{
	UserDto createUser(UserDto user);
	
	UserDto getUser(String email);
	
	UserDto getUserByUserId(String userId);
}
