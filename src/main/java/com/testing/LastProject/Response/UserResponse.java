package com.testing.LastProject.Response;

import java.util.UUID;

import com.testing.LastProject.model.User.Role;
import com.testing.LastProject.model.User.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
public class UserResponse {
	private UUID id;
	private String alias;
	private String cityId;
	private String email;
	private String name;
	private String phoneNumber;
	private Role role;
	private Status status;
}
