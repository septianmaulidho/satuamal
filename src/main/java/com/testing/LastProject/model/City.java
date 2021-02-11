package com.testing.LastProject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	@Column(length=36)
	private String id;
	
	@Column(length=50,nullable=false)
	private String cityName;
}
