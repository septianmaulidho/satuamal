package com.testing.LastProject.Controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.testing.LastProject.ErrorHandler.BadRequestException;
import com.testing.LastProject.ErrorHandler.MessageValid;
import com.testing.LastProject.Middleware.Middleware;
import com.testing.LastProject.model.Recipient;
import com.testing.LastProject.payload.RecipientPayload;
import com.testing.LastProject.Repository.RecipientRepository;
import com.testing.LastProject.Repository.UserRepository;
import com.testing.LastProject.Service.RecipientService;

@RestController
@RequestMapping("/api")
public class RecipientController {
	
	@Autowired
	RecipientService recipientService;	
	
	@Autowired
	RecipientRepository recipientRepo;
	
	@Autowired
	Middleware middleware;
	
	@GetMapping("/recipients")
	public ResponseEntity<List<Recipient>> read(@RequestHeader(value="authorization") String token){
		List<Recipient> recipients = recipientService.read();
		return new ResponseEntity<List<Recipient>>(recipients, HttpStatus.OK);
	}
	
	@PostMapping("/recipient/create")
	public ResponseEntity<Recipient> createRecipient(@RequestHeader(value="authorization") String token, @Valid @RequestBody RecipientPayload recipientPayload) throws BadRequestException{
		UUID userId = UUID.fromString(middleware.verifyJwt(token,"").getBody().get("userId").toString());
		Recipient recipient;
		try {			
			recipient = recipientService.create(userId, recipientPayload);
		} catch (BadRequestException e) {
			throw new BadRequestException(e.getMessage());
		}
		return new ResponseEntity<Recipient>(recipient, HttpStatus.OK);
	};
	
	@PostMapping("/recipient/update/{id}")
	public ResponseEntity<Recipient> updateRecipient(@RequestHeader(value="authorization") String token, @PathVariable("id") String id, @Valid @RequestBody RecipientPayload recipientPayload) throws BadRequestException{
		UUID userId = UUID.fromString(middleware.verifyJwt(token,"").getBody().get("userId").toString()); 
		Recipient recipient;
		try {			
			recipient = recipientService.update(userId, id, recipientPayload);
		} catch (BadRequestException e) {
			throw new BadRequestException(e.getMessage());
		}
		
		return new ResponseEntity<Recipient>(recipient, HttpStatus.OK);
	}
	
	@PostMapping("/recipient/upload/{id}")
	public ResponseEntity<String> uploadImages(@RequestHeader(value="authorization") String token, @PathVariable("id") String id, @Valid @RequestParam("images") MultipartFile images) throws IOException, BadRequestException{
		UUID userId = UUID.fromString(middleware.verifyJwt(token,"").getBody().get("userId").toString());
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
	public ResponseEntity<Recipient> readRecipient(@RequestHeader(value="authorization") String token, @PathVariable("id") String id) throws BadRequestException{
		Recipient recipient;
		try {			
			recipient = recipientService.readById(id);
		} catch (BadRequestException e) {
			throw new BadRequestException(e.getMessage());
		}
		return new ResponseEntity<Recipient>(recipient, HttpStatus.OK);
	}
	
	@DeleteMapping("/recipient/delete/{id}")
	public ResponseEntity<?> delete (@RequestHeader(value="authorization") String token, @PathVariable("id") String id) throws BadRequestException, IOException{
		UUID userId = UUID.fromString(middleware.verifyJwt(token,"").getBody().get("userId").toString());
		MessageValid message;
		try {			
			message = new MessageValid("recipient with name: " + recipientService.readById(id).getName() + " deleted successfully!");
			recipientService.delete(id);
		} catch (BadRequestException e) {
			throw new BadRequestException(e.getMessage());
		}
		return new ResponseEntity(message, HttpStatus.OK);
	}
	
}
