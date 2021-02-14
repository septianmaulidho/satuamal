package com.testing.LastProject.Response;

import java.util.UUID;

import com.testing.LastProject.model.City;
import com.testing.LastProject.model.User;
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
	private String email;
	private String name;
	private String phoneNumber;
	private Role role;
	private Status status;
	private City city;
	private User user;
	private String message;
	
	public UserResponse(String message) {
		this.message = message;
	}
	
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
	public User getUser() {
		return user;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
