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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Recipient extends Persistance{
	
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
	private String description;
	
	@Column(length = 50)
	@Enumerated(EnumType.STRING)
	private RecipientStatus recipientStatus;
	
	@JoinColumn(name = "city_id")
	@ManyToOne(targetEntity = City.class, fetch = FetchType.LAZY)
	@JsonIgnore
	private City city;
	
	@JoinColumn(name = "recipient_images_id")
	@ManyToOne(targetEntity = RecipientImages.class, fetch = FetchType.LAZY)
	@JsonIgnore
	private RecipientImages recipientImages;

	public Recipient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Recipient(String name, Date birthdate, String address, String description, RecipientStatus recipientStatus,
			City city) {
		super();
		this.name = name;
		this.birthdate = birthdate;
		this.address = address;
		this.description = description;
		this.recipientStatus = recipientStatus;
		this.city = city;
		this.setCreatedTime(new Date());
	}
	
	public Recipient(RecipientImages recipientImages) {
		super();
		this.recipientImages = recipientImages;
	}

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

	public RecipientImages getRecipientImages() {
		return recipientImages;
	}

	public void setRecipientImages(RecipientImages recipientImages) {
		this.recipientImages = recipientImages;
	}
	
}
