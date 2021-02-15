package com.testing.LastProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testing.LastProject.model.Recipient;

public interface RecipientRepository extends JpaRepository<Recipient, String>{

}
