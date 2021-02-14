package com.testing.LastProject.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue // Replace auto generate id from Integer to UUID
	private UUID id;
	
	@JoinColumn(name = "city_id")
	@ManyToOne(targetEntity = City.class, fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"id", "hibernateLazyInitializer"})
	private City city;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false, unique = true) // add unique constraint to email column to avoid duplicate entry
	private String email;
	
	@Column(nullable=false)
	private String password;
	
	@Column(nullable=false)
	private String alias;
	
	@Column(nullable=false)
	private String phoneNumber;
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private Status status;
	
	public enum Role {
		DONOR,VOLUNTEER,ADMIN // Change DONATURE to DONOR a more correct term
	}
	
	public enum Status{
		ACTIVE,NON_ACTIVE
	}
	
	public User() {
		
	}

	public User(City city, String name, String email, String password, String alias, String phoneNumber, Role role,
			Status status) {
		this.city = city;
		this.name = name;
		this.email = email;
		this.password = password;
		this.alias = alias;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.status = status;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	
}
