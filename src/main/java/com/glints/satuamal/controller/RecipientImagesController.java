package com.glints.satuamal.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.glints.satuamal.model.Recipient;
import com.glints.satuamal.model.RecipientImages;
import com.glints.satuamal.repository.RecipientImagesRepo;
import com.glints.satuamal.repository.RecipientRepo;
import com.glints.satuamal.service.CloudinaryService;
import com.glints.satuamal.service.RecipientImagesService;
import com.glints.satuamal.service.RecipientService;
import com.glints.satuamal.exception.Message;


@RestController
@RequestMapping("/api/recipient")
@CrossOrigin
public class RecipientImagesController {
	
	@Autowired
	CloudinaryService cloudinaryService;
	
	@Autowired
	RecipientImagesService recipientimagesService;
	
	@Autowired
	RecipientService recipientService;
	
	@Autowired
	RecipientImagesRepo recipientimagesRepo;
	
	@Autowired
	RecipientRepo recipientRepo;
	
	@GetMapping("/list-image")
	public ResponseEntity<List<RecipientImages>> list (){
		List<RecipientImages> list = recipientimagesService.list();
		return new ResponseEntity<List<RecipientImages>>(list, HttpStatus.OK);
	}
	
	@PostMapping("/upload-image/{id}")
	public ResponseEntity<?> upload(@PathVariable("id") Integer id, @RequestParam("image") MultipartFile image) throws IOException{
		Map result; 
		
		Recipient recipient = recipientRepo.findById(id).orElse(null);
		
		BufferedImage bi = ImageIO.read(image.getInputStream());
		if (!recipientService.exists(recipient.getId())) {
			return new ResponseEntity(new Message("Recipient with id: " + id + " not found!"), HttpStatus.BAD_REQUEST);
		}else if(bi == null){	
			return new ResponseEntity(new Message("image not valid"), HttpStatus.BAD_REQUEST);
		}else {
			
//			recipientImagesOld = recipientimagesRepo.getOne(recipient.getRecipientImages().getId());
//			recipientimagesService.delete(recipientImagesOld.getId());
//			cloudinaryService.delete(recipientImagesOld.getImageId());
			result = cloudinaryService.upload(image);
			RecipientImages recipientImages = new RecipientImages(
					(String) result.get("original_filename"),
					(String) result.get("url"),
					(String) result.get("public_id")
					);			
			recipientimagesService.save(recipientImages);
			
			RecipientImages recipientImagesNew = recipientimagesRepo.findById(recipientImages.getId()).orElse(null);
			
			
			recipient.setRecipientImages(recipientImagesNew);
			
			recipientRepo.save(recipient);
		}
		
		
		return new ResponseEntity(new Message("Images uploaded successfully!"), HttpStatus.OK);
	}
	
//	@DeleteMapping("/delete-image/{id}")
//	public ResponseEntity<?> delete(@PathVariable("id") Integer id) throws IOException{
//		if (!recipientimagesService.exists(id)) {
//			return new ResponseEntity(new Message("images with id: " + id + " doesn't exist!"), HttpStatus.BAD_REQUEST);
//		}
//		RecipientImages recipientImages = recipientimagesService.getOne(id).get();
//		Map result = cloudinaryService.delete(recipientImages.getImageId());
//		recipientimagesService.delete(id);
//		return new ResponseEntity<Map>(result, HttpStatus.OK);
//	}
	
}
