package com.testing.LastProject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
	@Id
	@Column(length=36)
	private String id;
	
	@JoinColumn(name = "city_id")
	@ManyToOne(targetEntity = City.class, fetch = FetchType.LAZY)
	@JsonIgnore
	private City city;
	
	@Column(length = 100, nullable=false)
	@Setter @Getter
	private String name;
	
	@Column(length = 100, nullable=false)
	private String email;
	
	@Column(length = 50, nullable=false)
	private String password;
	
	@Column(length = 50, nullable=false)
	private String alias;
	
	@Column(length = 50, nullable=false)
	private String phoneNumber;
	
	@Column(length = 50, nullable=false)
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Column(length = 50, nullable=false)
	@Enumerated(EnumType.STRING)
	private Status status;
	
	public enum Role {
		DONATUR,VOLUNTEER,ADMIN
	}
	
	public enum Status{
		ACTIVE,NON_ACTIVE
	}
}
