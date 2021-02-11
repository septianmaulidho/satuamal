package com.testing.LastProject.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="donation")
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class Donation {
	@Id
	@Column(length=36)
	private String id;
	
	@JoinColumn(name = "user_id")
	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;
	
	@JoinColumn(name = "recipient_id")
	@ManyToOne(targetEntity = Recipient.class, fetch = FetchType.LAZY)
	@JsonIgnore
	private Recipient recipient;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date acceptedDate;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date givenDate;
	
	@Column(length=255)
	private String photoUrl;
}
