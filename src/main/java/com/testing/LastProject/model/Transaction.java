package com.testing.LastProject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="transaction")
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor

public class Transaction {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid3")
	@Column(length=36, name = "id", unique = true)
	private String id;
	
//	@JoinColumn(name = "user_id")
//	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
//	@JsonIgnoreProperties(value = {"id", "hibernateLazyInitializer"})
//	private User user;
//	
//	@JoinColumn(name = "donation_id")
//	@ManyToOne(targetEntity = Donation.class, fetch = FetchType.LAZY)
//	@JsonIgnore
//	private Donation donation;
	
	@Column(length=255, name = "payment_evidence_foto_url")
	private String paymentPhotoUrl;
	
	public Transaction () {
		super();
	}
	
	public Transaction(
//			User user, 
//			Donation donation, 
			String paymentPhotoUrl) {
		super();
//		this.user = user;
//		this.donation = donation;
		this.paymentPhotoUrl = paymentPhotoUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//
//	public Donation getDonation() {
//		return donation;
//	}
//
//	public void setDonation(Donation donation) {
//		this.donation = donation;
//	}

	public String getPaymentPhotoUrl() {
		return paymentPhotoUrl;
	}

	public void setPaymentPhotoUrl(String paymentPhotoUrl) {
		this.paymentPhotoUrl = paymentPhotoUrl;
	}
	
	
}
