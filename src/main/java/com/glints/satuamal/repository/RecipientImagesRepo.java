package com.glints.satuamal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glints.satuamal.model.RecipientImages;

public interface RecipientImagesRepo extends JpaRepository<RecipientImages, Integer> {
	List<RecipientImages> findByOrderById();
}
