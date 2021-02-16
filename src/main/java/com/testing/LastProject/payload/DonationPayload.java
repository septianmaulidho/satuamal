package com.testing.LastProject.payload;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;




public class DonationPayload {
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date accepted_Date;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date given_Date;
	
	private String photo;
	
	private UUID user;


	public Date getAccepted_Date() {
		return accepted_Date;
	}

	public Date getGiven_Date() {
		return given_Date;
	}

	public String getPhoto() {
		return photo;
	}
	
	public UUID getUser() {
		return user;
	}
}

