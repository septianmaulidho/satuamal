package com.testing.LastProject.Service;

import java.util.UUID;

import com.testing.LastProject.ErrorHandler.AuthException;
import com.testing.LastProject.ErrorHandler.ResourceNotFoundException;
import com.testing.LastProject.Response.UserResponse;
import com.testing.LastProject.payload.UpdateUserPayload;
import com.testing.LastProject.payload.UserPayload;

public interface UserService {
	
	public UserResponse register(UserPayload userPayload) throws AuthException;
	public UserResponse login(UserPayload userPayload) throws AuthException;
	public UserResponse update(UUID id, UpdateUserPayload updateuserPayload) throws ResourceNotFoundException;
}
