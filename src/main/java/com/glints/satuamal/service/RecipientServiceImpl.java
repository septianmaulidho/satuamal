package com.glints.satuamal.service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.glints.satuamal.exception.BadRequestIdException;
import com.glints.satuamal.model.City;
import com.glints.satuamal.model.Recipient;
import com.glints.satuamal.payload.RecipientPayload;
import com.glints.satuamal.repository.CityRepo;
import com.glints.satuamal.repository.RecipientRepo;

@Service
public class RecipientServiceImpl implements RecipientService {
	
	@Autowired
	RecipientRepo recipientRepo;
	
	@Autowired
	CityRepo cityRepo;
	
	@Autowired
	CloudinaryService cloudinaryService;

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
		Recipient recipient = recipientRepo.findById(id).orElseThrow(() -> new BadRequestIdException("Recipient with id: " + id + " not found!"));
		cloudinaryService.delete(recipient.getImagesId());
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

	@Override
	public void uploadImages(Integer id, MultipartFile multipartFile) throws IOException {
		
		Map result; 
		
		Recipient recipient = recipientRepo.findById(id).orElse(null);
		
		BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
		if (!exists(recipient.getId())) {
			System.out.println("Recipient with id: " + id + " not found!");
		}else if(bi == null){	
			System.out.println("image not valid");
		} else {
			
			if(recipient.getImagesId() != null || recipient.getImagesUrl() != null) {
				cloudinaryService.delete(recipient.getImagesId());
			}
			result = cloudinaryService.upload(multipartFile);
			recipient.setImagesId((String) result.get("public_id"));
			recipient.setImagesUrl((String) result.get("url"));
			recipientRepo.save(recipient);		
			
			
			recipientRepo.save(recipient);
		}
		
	}

}
