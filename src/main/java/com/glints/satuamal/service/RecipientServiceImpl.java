package com.glints.satuamal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.glints.satuamal.exception.BadRequestIdException;
import com.glints.satuamal.model.City;
import com.glints.satuamal.model.Recipient;
import com.glints.satuamal.payload.RecipientPayload;
import com.glints.satuamal.repository.CityRepo;
import com.glints.satuamal.repository.RecipientRepo;

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
		City city = cityRepo.findById(recipientPayload.getCityId()).orElseThrow(() -> new BadRequestIdException("Recipient with id: " + recipientPayload.getCityId() + " not found!"));
		Recipient recipient = new Recipient(
								recipientPayload.getName(),
								recipientPayload.getBirthdate(),
								recipientPayload.getAddress(),
								recipientPayload.getPhotoUrl(),
								recipientPayload.getDescription(),
								recipientPayload.getRecipientStatus(),
								city
							);
		recipient = recipientRepo.save(recipient);
		return recipient;
	}

	@Override
	public Recipient update(Integer id, RecipientPayload recipientPayload) throws BadRequestIdException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(Integer id) throws BadRequestIdException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Recipient readById(Integer id) throws BadRequestIdException {
		// TODO Auto-generated method stub
		return null;
	}

}
