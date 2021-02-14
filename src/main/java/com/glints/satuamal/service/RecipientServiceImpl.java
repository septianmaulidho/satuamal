package com.glints.satuamal.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glints.satuamal.exception.BadRequestIdException;
import com.glints.satuamal.model.City;
import com.glints.satuamal.model.Recipient;
import com.glints.satuamal.model.RecipientImages;
import com.glints.satuamal.payload.RecipientPayload;
import com.glints.satuamal.repository.CityRepo;
import com.glints.satuamal.repository.RecipientImagesRepo;
import com.glints.satuamal.repository.RecipientRepo;

@Service
public class RecipientServiceImpl implements RecipientService {
	
	@Autowired
	RecipientRepo recipientRepo;
	
	@Autowired
	CityRepo cityRepo;

	@Override
	public List<Recipient> read() {
		List<Recipient> recipients = recipientRepo.findAll();
		return recipients;
	}

	@Override
	public Recipient create(RecipientPayload recipientPayload) throws BadRequestIdException {
		City city = cityRepo.findById(recipientPayload.getCityId()).orElseThrow(() -> new BadRequestIdException("City with id: " + recipientPayload.getCityId() + " not found!"));
		Recipient recipient = new Recipient(
							recipientPayload.getName(),
							recipientPayload.getBirthdate(),
							recipientPayload.getAddress(),
							recipientPayload.getDescription(),
							recipientPayload.getRecipientStatus(),
							city
						);
		recipient = recipientRepo.save(recipient);
		return recipient;
	}

	@Override
	public Recipient update(Integer id, RecipientPayload recipientPayload) throws BadRequestIdException {
		Recipient recipient = recipientRepo.findById(id).orElseThrow(() -> new BadRequestIdException("Recipient with id: " + id + " not found!"));
		City city = cityRepo.findById(recipientPayload.getCityId()).orElseThrow(() -> new BadRequestIdException("Recipient with id: " + recipientPayload.getCityId() + " not found!"));
		recipient.setName(recipientPayload.getName());
		recipient.setBirthdate(recipientPayload.getBirthdate());
		recipient.setAddress(recipientPayload.getAddress());
		recipient.setDescription(recipientPayload.getDescription());
		recipient.setCity(city);
		
		recipient = recipientRepo.save(recipient);
		return recipient;
	}

	@Override
	public void delete(Integer id) throws BadRequestIdException, IOException {
//		Recipient recipient = recipientRepo.findById(id).orElseThrow(() -> new BadRequestIdException("Recipient with id: " + id + " not found!"));
//		recipientimagesService.delete(recipient.getRecipientImages().getId());
		recipientRepo.deleteById(id);
	}

	@Override
	public Recipient readById(Integer id) throws BadRequestIdException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(Integer id) {
		return recipientRepo.existsById(id);
	}

}
