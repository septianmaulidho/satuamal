package com.glints.satuamal.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glints.satuamal.exception.BadRequestIdException;
import com.glints.satuamal.model.Recipient;
import com.glints.satuamal.payload.RecipientPayload;
import com.glints.satuamal.service.RecipientService;

@RestController
@RequestMapping("/api")
public class RecipientController {
	
	@Autowired
	RecipientService recipientService;
	
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
			throw new BadRequestIdException(e.getMessage());
		}
		return new ResponseEntity<Recipient>(recipient, HttpStatus.OK);
	};
	
}
