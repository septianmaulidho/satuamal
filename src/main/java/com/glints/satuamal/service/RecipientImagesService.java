package com.glints.satuamal.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glints.satuamal.exception.Message;
import com.glints.satuamal.model.RecipientImages;
import com.glints.satuamal.repository.RecipientImagesRepo;

@Service
public class RecipientImagesService {
	
	@Autowired
	CloudinaryService cloudinaryService;
	
	@Autowired
	RecipientImagesRepo recipientimagesRepo;
	
	public List<RecipientImages> list(){
		return recipientimagesRepo.findByOrderById();
	}
	
	public Optional<RecipientImages> getOne(Integer id){
		return recipientimagesRepo.findById(id);
	}
	
	public void save(RecipientImages recipientImages) {
		recipientimagesRepo.save(recipientImages);
	}
	
	public boolean exists(Integer id) {
		return recipientimagesRepo.existsById(id);
	}
	
	public void delete(Integer id) throws IOException {
		RecipientImages recipientImages = recipientimagesRepo.getOne(id);
		Message message;
		if (!exists(recipientImages.getId())) {
			message = new Message("images with id: " + id + " doesn't exist!");
		}
		cloudinaryService.delete(recipientImages.getImageId());
		recipientimagesRepo.deleteById(recipientImages.getId());
	}
}
