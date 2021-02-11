package com.testing.LastProject.model;

import java.util.Date;

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
@Table(name="recipient")
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class Recipient {
	@Id
	@Column(length=36)
	private String id;
	
	@JoinColumn(name = "user_id")
	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;
	
	@JoinColumn(name = "city_id")
	@ManyToOne(targetEntity = City.class, fetch = FetchType.LAZY)
	@JsonIgnore
	private City city;
	
	@Column(length=100,nullable=false)
	private String name;
	
	@Column(nullable=false)
	private Date birthDate;
	
	@Column(length=100,nullable=false)
	private String address;
	
	@Column(length=255)
	private String photoUrl;
	
	@Column(length = 50, nullable=false)
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Column(length=500,nullable=false)
	private String description;
	
	public enum Status{
		ACTIVE,NON_ACTIVE
	}
}
