package com.glints.satuamal.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.glints.satuamal.exception.BadRequestIdException;
import com.glints.satuamal.exception.Message;
import com.glints.satuamal.model.Recipient;
import com.glints.satuamal.payload.RecipientPayload;
import com.glints.satuamal.repository.RecipientRepo;
import com.glints.satuamal.service.RecipientService;

@RestController
@RequestMapping("/api")
public class RecipientController {
	
	@Autowired
	RecipientService recipientService;	
	
	@Autowired
	RecipientRepo recipientRepo;
	
	@GetMapping("/recipients")
	public ResponseEntity<List<Recipient>> read(){
		List<Recipient> recipients = recipientService.read();
		return new ResponseEntity<List<Recipient>>(recipients, HttpStatus.OK);
	}
	
	@PostMapping("/recipient/create")
	public ResponseEntity<Recipient> create(@Valid @RequestBody RecipientPayload recipientPayload) throws BadRequestIdException{
		Recipient recipient;
		try {			
			recipient = recipientService.create(recipientPayload);
		} catch (BadRequestIdException e) {
			System.out.println(e.getMessage());
			throw new BadRequestIdException(e.getMessage());
		}
		return new ResponseEntity<Recipient>(recipient, HttpStatus.OK);
	};
	
	@PostMapping("/recipient/upload/{id}")
	public ResponseEntity<String> uploadImages(@PathVariable("id") Integer id, @RequestParam("images") MultipartFile images) throws IOException{
		
		recipientService.uploadImages(id, images);
		Message message = new Message("Image upload successfully!");
		return new ResponseEntity(message, HttpStatus.OK);
	}
	
	@DeleteMapping("/recipient/delete/{id}")
	public ResponseEntity<?> delete (@PathVariable("id") Integer id) throws BadRequestIdException, IOException{
		recipientService.delete(id);
		Message message = new Message("recipient with id: " + id + " deleted successfully!");
		return new ResponseEntity(message, HttpStatus.OK);
	}
	
}
