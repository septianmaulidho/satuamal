package com.testing.LastProject.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testing.LastProject.ErrorHandler.ResourceNotFoundException;
import com.testing.LastProject.Repository.CityRepository;
import com.testing.LastProject.Repository.UserRepository;
import com.testing.LastProject.Response.UserResponse;
import com.testing.LastProject.model.City;
import com.testing.LastProject.model.User;
import com.testing.LastProject.payload.UpdateUserPayload;

@Service
public class UserServiceImplement implements UserService{
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	CityRepository cityRepo;
	
	@Override
	public UserResponse update(String id, UpdateUserPayload updateuserPayload) {
		// TODO Auto-generated method stub
		User user = userRepo.findById(id).orElseThrow( () -> new ResourceNotFoundException("User is not found"));
		user.setAlias(updateuserPayload.getAlias());
		user.setName(updateuserPayload.getName());
		user.setPhoneNumber(updateuserPayload.getPhoneNumber());
		City city = cityRepo.findById(updateuserPayload.getCityId()).orElseThrow( () -> new ResourceNotFoundException("User is not found"));
		user.setCity(city);
		user = userRepo.save(user);
		
		UserResponse userResponse = new UserResponse(
				user.getId(),
				user.getAlias(),
				user.getCity().getId(),
				user.getEmail(),
				user.getName(),
				user.getPhoneNumber(),
				user.getRole(),
				user.getStatus()
				);
		return userResponse;
	}
}
