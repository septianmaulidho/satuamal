package com.testing.LastProject.Service;

import com.testing.LastProject.Response.UserResponse;
import com.testing.LastProject.payload.UpdateUserPayload;

public interface UserService {
	public UserResponse update(String id, UpdateUserPayload updateuserPayload);
}
