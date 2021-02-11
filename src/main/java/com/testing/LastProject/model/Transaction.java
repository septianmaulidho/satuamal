package com.testing.LastProject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@Column(length=36)
	private String id;
	
	@JoinColumn(name = "user_id")
	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;
	
	@JoinColumn(name = "donation_id")
	@ManyToOne(targetEntity = Donation.class, fetch = FetchType.LAZY)
	@JsonIgnore
	private Donation donation;
	
	@Column(length=255)
	private String paymentPhotoUrl;
}
