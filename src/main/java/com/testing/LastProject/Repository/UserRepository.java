package com.testing.LastProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testing.LastProject.model.User;

public interface UserRepository extends JpaRepository<User,String>{

}
