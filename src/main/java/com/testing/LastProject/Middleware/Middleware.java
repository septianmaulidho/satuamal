package com.testing.LastProject.Middleware;

import javax.crypto.SecretKey;

import com.testing.LastProject.ErrorHandler.AuthException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class Middleware {
	
	private String token;

	public Middleware(String token) {
		this.token = token;
	}
	
	public Jws<Claims> verifyJwt() {
		String token = this.token.split(" ")[1];
		SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode("[B2492849238jmfsmdafidjffjadnfjdfhjdkj6df00d0a"));
		try {
		   Jws<Claims> jws = Jwts.parserBuilder()  // (1)
		    .setSigningKey(key)         // (2)
		    .build()                    // (3)
		    .parseClaimsJws(token); // (4)
		   
		   return jws;
		    
		} catch (Exception ex) {       // (5)
			throw new AuthException(ex.getMessage());
		}

	}
}
