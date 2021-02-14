package com.glints.satuamal.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "city")
public class City extends Persistance{
	
	public City() {
		super();
	}

	public City(String cityName) {
		this.cityName = cityName;
		this.setCreatedTime(new Date());
	}

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name ="uuid", strategy = "uuid2")
	@Column(name="id", unique = true)
	private String id;
	
	@Column(length = 30)
	private String cityName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
}
