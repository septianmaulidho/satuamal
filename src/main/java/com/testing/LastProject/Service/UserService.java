package com.testing.LastProject.Service;

import com.testing.LastProject.Response.UserResponse;
import com.testing.LastProject.payload.UpdateUserPayload;
import com.testing.LastProject.payload.UserPayload;

public interface UserService {
	public UserResponse register(UserPayload userPayload);
	public UserResponse update(String id, UpdateUserPayload updateuserPayload);
}
