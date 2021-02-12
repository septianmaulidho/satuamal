package com.testing.LastProject.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testing.LastProject.ErrorHandler.AuthException;
import com.testing.LastProject.ErrorHandler.ResourceNotFoundException;
import com.testing.LastProject.JWTconfig.JwtConstant;
import com.testing.LastProject.Repository.CityRepository;
import com.testing.LastProject.Repository.UserRepository;
import com.testing.LastProject.Response.UserResponse;
import com.testing.LastProject.model.City;
import com.testing.LastProject.model.User;
import com.testing.LastProject.payload.UpdateUserPayload;
import com.testing.LastProject.payload.UserPayload;

@Service
public class UserServiceImplement implements UserService {
	public final Pattern pattern = Pattern.compile("^(.+)@(.+)$");

	@Autowired
	UserRepository userRepo;

	@Autowired
	CityRepository cityRepo;

	@Override
	public UserResponse register(UserPayload userPayload) throws AuthException {
		try {
			City city = cityRepo.findBycityName(userPayload.getCity().toLowerCase());
			if (!pattern.matcher(userPayload.getEmail()).matches())
				throw new AuthException("Invalid email");
			if (city == null) {
				City newCity = new City(userPayload.getCity().toLowerCase());
				cityRepo.save(newCity);
				String hashPassword = BCrypt.hashpw(userPayload.getPassword(), BCrypt.gensalt(10));
				User user = new User(newCity, userPayload.getName(), userPayload.getEmail().toLowerCase(), hashPassword,
						userPayload.getAlias(), userPayload.getPhoneNumber(), userPayload.getRole(),
						userPayload.getStatus());
				userRepo.save(user);
				UserResponse userResponse = new UserResponse(user.getId(), city, user.getName(), user.getAlias(),
						user.getEmail(), user.getPhoneNumber(), user.getRole(), user.getStatus());
				return userResponse;
			} else {
				String hashPassword = BCrypt.hashpw(userPayload.getPassword(), BCrypt.gensalt(10));
				User user = new User(city, userPayload.getName(), userPayload.getEmail().toLowerCase(), hashPassword,
						userPayload.getAlias(), userPayload.getPhoneNumber(), userPayload.getRole(),
						userPayload.getStatus());
				userRepo.save(user);
				UserResponse userResponse = new UserResponse(user.getId(), city, user.getName(), user.getAlias(),
						user.getEmail(), user.getPhoneNumber(), user.getRole(), user.getStatus());
				return userResponse;
			}
		} catch (Exception e) {
			throw new AuthException("Email already in use");
		}
	}

	@Override
	public UserResponse login(UserPayload userPayload) throws AuthException {
		User user = userRepo.findByEmail(userPayload.getEmail());
		if(BCrypt.checkpw(userPayload.getPassword(), user.getPassword())) {
			UserResponse userResponse = new UserResponse(user.getId(), user.getName(), user.getAlias(),
					user.getEmail(), user.getPhoneNumber(), user.getRole(), user.getStatus());
			return userResponse;
		}else {
			throw new AuthException("User unauthorized");
		}
	}

	@Override
	public UserResponse update(UUID id, UpdateUserPayload updateuserPayload) {

		User user = userRepo.findById(id);
		user.setAlias(updateuserPayload.getAlias());
		user.setName(updateuserPayload.getName());
		user.setPhoneNumber(updateuserPayload.getPhoneNumber());
		City city = cityRepo.findById(updateuserPayload.getCityId())
				.orElseThrow(() -> new ResourceNotFoundException("User is not found"));
		user.setCity(city);
		user = userRepo.save(user);
		UserResponse userResponse = new UserResponse(user.getId(), user.getCity(), user.getAlias(), user.getEmail(),
				user.getName(), user.getPhoneNumber(), user.getRole(), user.getStatus());
		return userResponse;
	}
}
