package com.testing.LastProject.payload;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.testing.LastProject.model.Recipient;

import lombok.Data;

@Data
public class RecipientPayload {
	
	@NotBlank(message = "Recipient name is required!")
	@Size(min = 3, message = "Recipient name must be 3 or more characters!")
	private String name;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "GMT+7")
	private Date birthdate;
	
	@NotBlank(message = "Recipient name is required!")
	@Size(min = 3, message = "Recipient name must be 3 or more characters!")
	private String address;
	
	@NotBlank(message = "Recipient name is required!")
	@Size(min = 3, message = "Recipient name must be 3 or more characters!")
	private String description;
	
	@NotNull(message = "Recipient birthdate is required!")
	private Recipient.RecipientStatus recipientStatus;
	
	@NotBlank(message = "city is required!")
	private String city;
	
	private String imageId;
	
	private String imageUrl;

	public String getName() {
		return name;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public String getAddress() {
		return address;
	}

	public String getDescription() {
		return description;
	}

	public Recipient.RecipientStatus getRecipientStatus() {
		return recipientStatus;
	}

	public String getCity() {
		return city;
	}

	public String getImageId() {
		return imageId;
	}

	public String getImageUrl() {
		return imageUrl;
	}
	
}
