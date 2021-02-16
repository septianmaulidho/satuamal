package com.testing.LastProject.Service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.testing.LastProject.ErrorHandler.BadRequestException;
import com.testing.LastProject.Repository.CityRepository;
import com.testing.LastProject.Repository.RecipientRepository;
import com.testing.LastProject.Repository.UserRepository;
import com.testing.LastProject.model.City;
import com.testing.LastProject.model.Recipient;
import com.testing.LastProject.model.User;
import com.testing.LastProject.payload.RecipientPayload;

@Service
public class RecipientServiceImplement implements RecipientService {
	
	@Autowired
	RecipientRepository recipientRepo;
	
	@Autowired
	CityRepository cityRepo;
	
	@Autowired
	CloudinaryService cloudinaryService;
	
	@Autowired
	UserRepository userRepo;

	@Override
	public List<Recipient> read() {
		List<Recipient> recipients = recipientRepo.findAll();
		return recipients;
	}

	@Override
	public Recipient create(UUID userId, RecipientPayload recipientPayload) throws BadRequestException {
		User user = userRepo.findById(userId).orElseThrow(()-> new BadRequestException("User not found"));
		City city = cityRepo.findBycityName(recipientPayload.getCity().toLowerCase());
		Recipient recipient;
		if(city == null) {
			City newCity = new City(recipientPayload.getCity().toLowerCase());
			cityRepo.save(newCity);
			recipient = new Recipient(
					recipientPayload.getName(),
					recipientPayload.getBirthdate(),
					recipientPayload.getAddress(),
					recipientPayload.getDescription(),
					recipientPayload.getRecipientStatus(),
					newCity,
					user
				);
		} else {
			recipient = new Recipient(
					recipientPayload.getName(),
					recipientPayload.getBirthdate(),
					recipientPayload.getAddress(),
					recipientPayload.getDescription(),
					recipientPayload.getRecipientStatus(),
					city,
					user
				);			
		}
		recipient = recipientRepo.save(recipient);
		return recipient;
	}

	@Override
	public Recipient update(UUID userId, String id, RecipientPayload recipientPayload) throws BadRequestException {
		User user = userRepo.findById(userId).orElseThrow(()-> new BadRequestException("User not found"));
		Recipient recipient = recipientRepo.findById(id).orElseThrow(() -> new BadRequestException("Recipient with id: " + id + " not found!"));
		City city = cityRepo.findBycityName(recipientPayload.getCity().toLowerCase());
		
		if(city == null) {
			City newCity = new City(recipientPayload.getCity().toLowerCase());
			cityRepo.save(newCity);
			recipient.setName(recipientPayload.getName());
			recipient.setBirthdate(recipientPayload.getBirthdate());
			recipient.setAddress(recipientPayload.getAddress());
			recipient.setDescription(recipientPayload.getDescription());
			recipient.setCity(newCity);
			recipient.setUser(user);
			recipient.setUpdatedTime(new Date());
		} else {			
			recipient.setName(recipientPayload.getName());
			recipient.setBirthdate(recipientPayload.getBirthdate());
			recipient.setAddress(recipientPayload.getAddress());
			recipient.setDescription(recipientPayload.getDescription());
			recipient.setCity(city);
			recipient.setUser(user);
			recipient.setUpdatedTime(new Date());
		}		
		recipient = recipientRepo.save(recipient);
		return recipient;
	}

	@Override
	public Recipient readById(String id) throws BadRequestException {
		Recipient recipient = recipientRepo.findById(id).orElseThrow(() -> new BadRequestException("Recipient with id: " + id + " not found!"));
		return recipient;
	}
	
	@Override
	public void uploadImages(String id, MultipartFile multipartFile) throws IOException, BadRequestException {
		
		Map result; 
		
		Recipient recipient = recipientRepo.findById(id).orElseThrow(() -> new BadRequestException("Recipient with id: " + id + " not found!"));
		
		BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
		if (!exists(recipient.getId())) {
			throw new BadRequestException("Recipient with id: " + id + " not found!");
		}else if(bi == null){	
			throw new BadRequestException("Image is required!");
		} else {
			
			if(recipient.getImagesId() != null || recipient.getImagesUrl() != null) {
				cloudinaryService.delete(recipient.getImagesId());
			}
			result = cloudinaryService.upload(multipartFile);
			recipient.setImagesId((String) result.get("public_id"));
			recipient.setImagesUrl((String) result.get("url"));
			recipient.setUpdatedTime(new Date());
			recipientRepo.save(recipient);
		}
		
	}
	
	@Override
	public void delete(String id) throws BadRequestException, IOException {
		Recipient recipient = recipientRepo.findById(id).orElseThrow(() -> new BadRequestException("Recipient with id: " + id + " not found!"));
		if(recipient.getImagesId() != null || recipient.getImagesUrl() != null) {			
			cloudinaryService.delete(recipient.getImagesId());
		}
		recipientRepo.deleteById(id);
	}

	@Override
	public boolean exists(String id) {
		return recipientRepo.existsById(id);
	}

}
