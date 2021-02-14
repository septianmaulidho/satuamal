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

import com.glints.satuamal.exception.BadRequestException;
import com.glints.satuamal.exception.MessageValid;
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
	public ResponseEntity<Recipient> createRecipient(@Valid @RequestBody RecipientPayload recipientPayload) throws BadRequestException{
		Recipient recipient;
		try {			
			recipient = recipientService.create(recipientPayload);
		} catch (BadRequestException e) {
			throw new BadRequestException(e.getMessage());
		}
		return new ResponseEntity<Recipient>(recipient, HttpStatus.OK);
	};
	
	@PostMapping("/recipient/update/{id}")
	public ResponseEntity<Recipient> updateRecipient(@PathVariable("id") Integer id, @Valid @RequestBody RecipientPayload recipientPayload) throws BadRequestException{
		
		Recipient recipient;
		try {			
			recipient = recipientService.update(id, recipientPayload);
		} catch (BadRequestException e) {
			throw new BadRequestException(e.getMessage());
		}
		
		return new ResponseEntity<Recipient>(recipient, HttpStatus.OK);
	}
	
	@PostMapping("/recipient/upload/{id}")
	public ResponseEntity<String> uploadImages(@PathVariable("id") Integer id, @Valid @RequestParam("images") MultipartFile images) throws IOException, BadRequestException{
		MessageValid message;
		try {			
			recipientService.uploadImages(id, images);
			message = new MessageValid("Image upload successfully!");
		} catch (BadRequestException e) {
			throw new BadRequestException(e.getMessage());
		}
		return new ResponseEntity(message, HttpStatus.OK);
	}
	
	@GetMapping("/recipient/{id}")
	public ResponseEntity<Recipient> readRecipient(@PathVariable("id") Integer id) throws BadRequestException{
		Recipient recipient;
		try {			
			recipient = recipientService.readById(id);
		} catch (BadRequestException e) {
			throw new BadRequestException(e.getMessage());
		}
		return new ResponseEntity<Recipient>(recipient, HttpStatus.OK);
	}
	
	@DeleteMapping("/recipient/delete/{id}")
	public ResponseEntity<?> delete (@PathVariable("id") Integer id) throws BadRequestException, IOException{		
		MessageValid message;
		try {			
			recipientService.delete(id);
			message = new MessageValid("recipient with id: " + id + " deleted successfully!");
		} catch (BadRequestException e) {
			throw new BadRequestException(e.getMessage());
		}
		return new ResponseEntity(message, HttpStatus.OK);
	}
	
}
