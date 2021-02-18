package com.testing.LastProject.model;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "Donation")
public class Donation {
	
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name ="uuid", strategy = "uuid2")
	@Column(name="id", unique = true)
	private  String id;
	
	@Column
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date accepted_Date;
	
	@Column
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date given_Date;
	
	@Column
	private String photo;
	
	@JoinColumn(name = "user_id")
	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;
	
	@JoinColumn(name = "recipient_id")
	@ManyToOne(targetEntity = Recipient.class, fetch = FetchType.LAZY)
	@JsonIgnore
	private Recipient recipient;
	
	public Donation() {
		super();
	}

	public Donation(Date accepted_Date, Date given_Date, String photo, User user, Recipient recipient) {
		super();
		this.accepted_Date = accepted_Date;
		this.given_Date = given_Date;
		this.photo = photo;
		this.user = user;
		this.recipient = recipient;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getAccepted_Date() {
		return accepted_Date;
	}
	public void setAccepted_Date(Date accepted_Date) {
		this.accepted_Date = accepted_Date;
	}
	public Date getGiven_Date() {
		return given_Date;
	}
	public void setGiven_Date(Date given_Date) {
		this.given_Date = given_Date;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public Recipient getRecipient() {
		return recipient;
	}
	public void setRecipient(Recipient recipient) {
		this.recipient = recipient;
	}
	
	
}
