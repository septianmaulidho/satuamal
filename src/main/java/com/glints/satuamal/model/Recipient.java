package com.glints.satuamal.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Recipient extends Persistance{
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name ="uuid", strategy = "uuid2")
	@Column(name="id", unique = true)
	private String id;
	
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
	
	private String imagesId;
	private String imagesUrl;

	public Recipient() {
		super();
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
	
	public Recipient(String imagesId, String ImagesUrl) {
		super();
		this.imagesId = imagesId;
		this.imagesUrl = ImagesUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getImagesId() {
		return imagesId;
	}

	public void setImagesId(String imagesId) {
		this.imagesId = imagesId;
	}

	public String getImagesUrl() {
		return imagesUrl;
	}

	public void setImagesUrl(String imagesUrl) {
		this.imagesUrl = imagesUrl;
	}
	
}
