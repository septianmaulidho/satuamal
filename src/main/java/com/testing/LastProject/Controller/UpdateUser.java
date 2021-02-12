package com.testing.LastProject.Controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testing.LastProject.Response.UserResponse;
import com.testing.LastProject.Service.UserService;
import com.testing.LastProject.payload.UpdateUserPayload;

@RestController
@RequestMapping("user/update")
public class UpdateUser {
	@Autowired
	UserService userService;
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> editUser(@PathVariable("id") UUID id, @RequestBody UpdateUserPayload updateUserPayload){
		UserResponse user = userService.update(id, updateUserPayload);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
}
