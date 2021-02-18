package com.testing.LastProject.Response;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



import org.hibernate.annotations.GenericGenerator;



@Entity
@Table(name = "Picture")
public class FileUploadResponse {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name ="uuid", strategy = "uuid2")
	@Column(name="id", unique = true)
	private  String id;
	
	@Column
	private String photo;
	
	
	
	public FileUploadResponse() {
		super();
	}
	public FileUploadResponse(String photo) {
		super();
		this.photo = photo;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
}
