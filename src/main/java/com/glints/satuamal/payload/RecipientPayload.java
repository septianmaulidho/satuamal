package com.glints.satuamal.payload;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.glints.satuamal.model.Recipient;

import lombok.Data;

@Data
public class RecipientPayload {
	
	@NotBlank(message = "Recipient name is required!")
	@Size(min = 3, message = "Recipient name must be 3 or more characters!")
	private String name;
	
	@DateTimeFormat(pattern = "dd-mm-yyyy")
	private Date birthdate;
	
	@NotBlank(message = "Address is required!")
	@Size(min = 3, message = "Address must be 3 or more characters!")
	private String address;
	
	@NotBlank(message = "Photo is required!")
	private String photoUrl;
	
	@NotBlank(message = "Address is required!")
	@Size(min = 6, message = "Address must be 6 or more characters!")
	private String description;
	
	@NotEmpty(message = "Recipient Status is required!")
	private Recipient.RecipientStatus recipientStatus;
	
	@Min(1)
	private Integer cityId;
	
	public String getName() {
		return name;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public String getAddress() {
		return address;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public String getDescription() {
		return description;
	}
	public Recipient.RecipientStatus getRecipientStatus() {
		return recipientStatus;
	}
	public Integer getCityId() {
		return cityId;
	}
	
}
