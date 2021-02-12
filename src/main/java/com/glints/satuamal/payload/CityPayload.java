package com.glints.satuamal.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CityPayload {
	
	@NotBlank(message = "City is required!")
	@Size(min = 3, message = "City name must be 3 or more characters!")
	private String cityName;

	public String getCityName() {
		return cityName;
	}
	
}
