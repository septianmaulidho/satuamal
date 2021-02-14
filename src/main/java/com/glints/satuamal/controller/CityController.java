package com.glints.satuamal.controller;

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

import com.glints.satuamal.exception.BadRequestException;
import com.glints.satuamal.exception.MessageValid;
import com.glints.satuamal.model.City;
import com.glints.satuamal.payload.CityPayload;
import com.glints.satuamal.repository.CityRepo;
import com.glints.satuamal.service.CityService;

@RestController
@RequestMapping("/api")
public class CityController {
	
	@Autowired
	CityService cityService;
	
	@GetMapping("/cities")
	public ResponseEntity<List<City>> read(){
		List<City> cities = cityService.read();
		return new ResponseEntity<List<City>>(cities, HttpStatus.OK);
	}
	
	@PostMapping("/city/create")
	public ResponseEntity<City> create(@Valid @RequestBody CityPayload cityPayload){
		City city = cityService.create(cityPayload);
		return new ResponseEntity<City>(city, HttpStatus.OK);
	}
	
	@PostMapping("/city/update/{id}")
	public ResponseEntity<City> update(@PathVariable("id") String id, @Valid @RequestBody CityPayload cityPayload) throws BadRequestException {
		City city; 
		try {
			city = cityService.update(id, cityPayload);			
		} catch (BadRequestException e) {
			throw new BadRequestException(e.getMessage());
		}
		return new ResponseEntity<City>(city, HttpStatus.OK);
	}
	
	@DeleteMapping("/city/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id) throws BadRequestException {
		MessageValid message;
		try {
			message = new MessageValid(cityService.readById(id).getCityName() + " city deleted successfully!");
			cityService.delete(id);
		} catch (BadRequestException e) {
			throw new BadRequestException(e.getMessage());
		}
		return new ResponseEntity(message, HttpStatus.OK);
	}
	
	@GetMapping("/city/{id}")
	public ResponseEntity<?> readById(@Valid @PathVariable("id") String id) throws BadRequestException {
		City city; 
		try {
			city = cityService.readById(id);			
		} catch (BadRequestException e) {
			throw new BadRequestException(e.getMessage());
		}
		return new ResponseEntity<City>(city, HttpStatus.OK);
	}
	
}
