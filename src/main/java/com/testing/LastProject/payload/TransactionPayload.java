package com.testing.LastProject.payload;

import lombok.Data;

@Data
public class TransactionPayload {
	
//	private String user;
//	private String donation;
	private String paymentPhotoUrl;
	
//	public String getUser() {
//		return user;
//	}
//	public String getDonation() {
//		return donation;
//	}
	public String getPaymentPhotoUrl() {
		return paymentPhotoUrl;
	}
	
	
}
