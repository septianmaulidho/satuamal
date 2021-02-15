package com.testing.LastProject.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testing.LastProject.ErrorHandler.AuthException;
import com.testing.LastProject.Middleware.MiddlewareInterface;
import com.testing.LastProject.Response.UserResponse;
import com.testing.LastProject.Service.UserService;
import com.testing.LastProject.payload.UserPayload;

@RestController
@RequestMapping("/api/user")
public class AuthController {

	@Autowired
	UserService userService;
	
	@Autowired
	MiddlewareInterface middleware;
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody UserPayload userPayload){
		UserResponse user = userService.register(userPayload);
		user.setToken(middleware.generateJWTToken(user));
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserPayload userPayload) throws AuthException {
		try {
			UserResponse user = userService.login(userPayload);
			user.setToken(middleware.generateJWTToken(user));
			return new ResponseEntity<>(user, HttpStatus.OK);
		}catch (Exception e) {
			System.out.println(e);
			Map<String, String> map = new HashMap<>();
			map.put("message", "user unauthorized");
			return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
		}
		
	}
}
