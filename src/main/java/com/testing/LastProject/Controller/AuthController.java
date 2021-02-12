package com.testing.LastProject.Controller;

import java.security.Key;
import java.security.Security;
import java.util.Date;
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
import com.testing.LastProject.JWTconfig.JwtConstant;
import com.testing.LastProject.Response.UserResponse;
import com.testing.LastProject.Service.UserService;
import com.testing.LastProject.payload.UserPayload;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@RestController
@RequestMapping("/api/user")
public class AuthController {
	
	public Map<String, String> generateJWTToken(UserResponse userResponse){
		long timestamp = System.currentTimeMillis();
		Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		@SuppressWarnings("deprecation")
		String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, key)
				.setIssuedAt(new Date(timestamp))
				.setExpiration(new Date(timestamp + JwtConstant.TOKEN_VALIDITY))
				.claim("userId", userResponse.getId())
				.claim("name", userResponse.getName())
				.claim("email", userResponse.getEmail())
				.claim("phoneNumber", userResponse.getPhoneNumber())
				.claim("role", userResponse.getRole())
				.claim("status", userResponse.getStatus())
				.compact();
		Map<String, String> map = new HashMap<>();
		map.put("token", token);
		return map;
	}

	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody UserPayload userPayload){
		UserResponse user = userService.register(userPayload);
		return new ResponseEntity<>(generateJWTToken(user), HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserPayload userPayload) throws AuthException {
		try {
			UserResponse user = userService.login(userPayload);
//			Map<String, String> map = new HashMap<>();
//			map.put("message", "login succesfull");
			return new ResponseEntity<>(generateJWTToken(user), HttpStatus.OK);
		}catch (Exception e) {
			Map<String, String> map = new HashMap<>();
			map.put("message", "user unauthorized");
			return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
		}
		
	}
}
