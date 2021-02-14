package com.testing.LastProject.payload;

import com.testing.LastProject.model.User;
import com.testing.LastProject.model.User.Role;
import com.testing.LastProject.model.User.Status;

import lombok.Data;

@Data
public class UserPayload {
	private String city;
	private String name;
	private String email;
	private String password;
	private String alias;
	private String phoneNumber;
	private User.Role role;
	private User.Status status;
	
	public String getCity() {
		return city;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getAlias() {
		return alias;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public User.Role getRole() {
		return role;
	}
	public User.Status getStatus() {
		return status;
	}
}
