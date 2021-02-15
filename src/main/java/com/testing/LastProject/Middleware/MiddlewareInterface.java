package com.testing.LastProject.Middleware;

import com.testing.LastProject.Response.UserResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public interface MiddlewareInterface {
	public String generateJWTToken(UserResponse userResponse);
	public Jws<Claims> verifyJwt(String token,String role);
}
