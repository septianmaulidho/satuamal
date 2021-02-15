package com.testing.LastProject.Controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testing.LastProject.Middleware.Middleware;
import com.testing.LastProject.Response.UserResponse;
import com.testing.LastProject.Service.UserService;
import com.testing.LastProject.payload.UpdateUserPayload;

@RestController
@RequestMapping("user/update")
public class UpdateUserController {
	@Autowired
	UserService userService;
	
	@Autowired
	Middleware middleware;
	
	@PatchMapping
	public ResponseEntity<?> editUser(@RequestHeader(value="authorization") String token, @RequestBody UpdateUserPayload updateUserPayload){
		/* Impement Middleware to user/update endpoint
		verifyJwt(String token, String role)
		token is get from @RequestHeader, role is (ADMIN,DONOR,VOLUNTEER, or ""->wildcard */
		
		UUID id = UUID.fromString(middleware.verifyJwt(token,"").getBody().get("userId").toString()); 
		UserResponse user = userService.update(id, updateUserPayload);
		user.setToken(middleware.generateJWTToken(user));
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
}
