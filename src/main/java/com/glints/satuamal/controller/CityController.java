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

import com.glints.satuamal.exception.BadRequestIdException;
import com.glints.satuamal.model.City;
import com.glints.satuamal.payload.CityPayload;
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
	
	@PostMapping("/create-city")
	public ResponseEntity<City> create(@Valid @RequestBody CityPayload cityPayload){
		City city = cityService.create(cityPayload);
		return new ResponseEntity<City>(city, HttpStatus.OK);
	}
	
	@PostMapping("/update-city/{id}")
	public ResponseEntity<City> update(@PathVariable("id") Integer id, @Valid @RequestBody CityPayload cityPayload) throws BadRequestIdException {
		City city; 
		try {
			city = cityService.update(id, cityPayload);			
		} catch (BadRequestIdException e) {
			throw new BadRequestIdException(e.getMessage());
		}
		return new ResponseEntity<City>(city, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete-city/{id}")
	public ResponseEntity<?> delete(@Valid @PathVariable("id") Integer id) throws BadRequestIdException {
		String city; 
		try {
			city = cityService.delete(id);			
		} catch (BadRequestIdException e) {
			throw new BadRequestIdException(e.getMessage());
		}
		return new ResponseEntity<String>(city, HttpStatus.OK);
	}
	
	@GetMapping("/city/{id}")
	public ResponseEntity<?> readById(@Valid @PathVariable("id") Integer id) throws BadRequestIdException {
		City city; 
		try {
			city = cityService.readById(id);			
		} catch (BadRequestIdException e) {
			throw new BadRequestIdException(e.getMessage());
		}
		return new ResponseEntity<City>(city, HttpStatus.OK);
	}
	
}
