package com.nat.shoppinglist.repository;

import org.springframework.stereotype.Repository;

import com.nat.shoppinglist.models.entities.UserEntity;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
	UserEntity findByEmail(String email);
	
	UserEntity findByUserId(String userId);
}
