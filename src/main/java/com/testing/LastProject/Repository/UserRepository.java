package com.testing.LastProject.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testing.LastProject.model.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	User findByEmail(String email); // Add method to lookup existing user by email
	User findById(UUID id);
}
