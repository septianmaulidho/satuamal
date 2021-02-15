package com.testing.LastProject.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="city")
public class City {
	
	@Id
	@GeneratedValue
	private UUID id; // change generated id to UUID type
	
	@Column(length=50,nullable=false)
	private String cityName;
	
	public City() {
		
	}
	public City(String cityName) {
		super();
		this.cityName = cityName.toLowerCase();
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
}
