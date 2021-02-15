package com.testing.LastProject.Middleware;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.testing.LastProject.ErrorHandler.AuthException;
import com.testing.LastProject.JWTconfig.JwtConstant;
import com.testing.LastProject.Response.UserResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class Middleware implements MiddlewareInterface{
	
	@Value("${JWTSecret}")
	private String JWTSecret;
	
	public String generateJWTToken(UserResponse userResponse){
		long timestamp = System.currentTimeMillis();
		SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(JWTSecret));
		@SuppressWarnings("deprecation")
		String token = Jwts.builder().signWith(key)
				.setIssuedAt(new Date(timestamp))
				.setExpiration(new Date(timestamp + JwtConstant.TOKEN_VALIDITY))
				.claim("userId", userResponse.getId())
				.claim("name", userResponse.getName())
				.claim("email", userResponse.getEmail())
				.claim("phoneNumber", userResponse.getPhoneNumber())
				.claim("role", userResponse.getRole())
				.claim("status", userResponse.getStatus())
				.compact();
		
		return token;
	}
	
	public Jws<Claims> verifyJwt(String token, String role) {
		SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(JWTSecret));
		try {
			token = token.split(" ")[1];
		   Jws<Claims> jws = Jwts.parserBuilder()
		    .setSigningKey(key)
		    .build()                  
		    .parseClaimsJws(token);
		   
		   if(!role.isEmpty() && !role.equals(jws.getBody().get("role").toString())) {
			   throw new AuthException("Access Denied!");
		   }
		   return jws;
		    
		} catch (Exception ex) { 
			if(ex.getMessage().equals("Access Denied!"))
				throw new AuthException(ex.getMessage());
			else
				throw new AuthException("Invalid token, please re-login");
		}

	}
}
