package com.testing.LastProject.Response;

import java.util.UUID;

import com.testing.LastProject.model.City;
import com.testing.LastProject.model.User.Role;
import com.testing.LastProject.model.User.Status;


public class UserResponse {

	private UUID id;
	private String alias;
	private String email;
	private String name;
	private String phoneNumber;
	private Role role;
	private Status status;
	private City city;
	private String token;
	
	public UserResponse(UUID id, City city, String name, String alias,  String email, String phoneNumber, Role role,
			Status status) {
		this.id = id;
		this.alias = alias;
		this.email = email;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.status = status;
		this.city = city;
	}
	
	public UserResponse(UUID id, String name, String alias,  String email, String phoneNumber, Role role,
			Status status) {
		this.id = id;
		this.alias = alias;
		this.email = email;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.status = status;
	}
	public UUID getId() {
		return id;
	}
	public String getAlias() {
		return alias;
	}
	public String getEmail() {
		return email;
	}
	public String getName() {
		return name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public Role getRole() {
		return role;
	}
	public Status getStatus() {
		return status;
	}
	public City getCity() {
		return city;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
}
