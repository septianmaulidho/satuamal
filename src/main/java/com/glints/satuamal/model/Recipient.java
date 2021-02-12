package com.glints.satuamal.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "repicient")
public class Recipient extends Persistance {
	
	public Recipient() {
		super();
	}

	public Recipient(String name, Date birthdate, String address, String photoUrl, String description,
			RecipientStatus recipientStatus, City city) {
		super();
		this.name = name;
		this.birthdate = birthdate;
		this.address = address;
		this.photoUrl = photoUrl;
		this.description = description;
		this.recipientStatus = recipientStatus;
		this.city = city;
		this.setCreatedTime(new Date());
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;
	
	public enum RecipientStatus {
		ACTIVE, NONACTIVE, PROGRESS, REJECT
	}
	
	@Column(length = 100)
	private String name;
	
	@Column
	private Date birthdate;
	
	@Column(length = 255)
	private String address;
	
	@Column(length = 255)
	private String photoUrl;
	
	@Column(length = 255)
	private String description;
	
	@Column(length = 50)
	@Enumerated(EnumType.STRING)
	private RecipientStatus recipientStatus;
	
//	@JoinColumn(name = "user_id")
//	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
//	@JsonIgnore
//	private User user;
	
	@JoinColumn(name = "city_id")
	@ManyToOne(targetEntity = City.class, fetch = FetchType.LAZY)
	@JsonIgnore
	private City city;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public RecipientStatus getRecipientStatus() {
		return recipientStatus;
	}

	public void setRecipientStatus(RecipientStatus recipientStatus) {
		this.recipientStatus = recipientStatus;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
}
