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
import org.springframework.web.bind.annotation.RestController;

import com.glints.satuamal.exception.BadRequestIdException;
import com.glints.satuamal.exception.Message;
import com.glints.satuamal.model.Recipient;
import com.glints.satuamal.payload.RecipientPayload;
import com.glints.satuamal.repository.RecipientRepo;
import com.glints.satuamal.service.RecipientImagesService;
import com.glints.satuamal.service.RecipientService;

@RestController
@RequestMapping("/api")
public class RecipientController {
	
	@Autowired
	RecipientService recipientService;
	
	@Autowired
	RecipientRepo recipientRepo;
	
	@Autowired
	RecipientImagesService recipientimagesService;
	
	@GetMapping("/recipients")
	public ResponseEntity<List<Recipient>> create(){
		List<Recipient> recipients = recipientService.read();
		return new ResponseEntity<List<Recipient>>(recipients, HttpStatus.OK);
	}
	
	@PostMapping("/create-recipient")
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
	
	@DeleteMapping("/delete-recipient/{id}")
	public ResponseEntity<?> delete (@PathVariable("id") Integer id) throws BadRequestIdException, IOException{
		Recipient recipient = recipientRepo.findById(id).orElseThrow(() -> new BadRequestIdException("Recipient with id: " + id + " not found!"));
		recipientimagesService.delete(recipient.getRecipientImages().getId());
		recipientService.delete(id);
		return new ResponseEntity(new Message("recipient with id: " + id + " deleted successfully!"), HttpStatus.OK);
	}
	
}
