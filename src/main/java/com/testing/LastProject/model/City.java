package com.testing.LastProject.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="city")
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class City {
	
	@Id
	@GeneratedValue
	private UUID id; // change generated id to UUID type
	
	@Column(length=50,nullable=false)
	private String cityName;
}
