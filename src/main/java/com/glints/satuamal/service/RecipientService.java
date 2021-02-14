package com.glints.satuamal.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.glints.satuamal.exception.BadRequestException;
import com.glints.satuamal.model.Recipient;
import com.glints.satuamal.payload.RecipientPayload;

public interface RecipientService {
	public List<Recipient> read();
	public Recipient create(RecipientPayload recipientPayload) throws BadRequestException;
	public Recipient update(String id, RecipientPayload recipientPayload) throws BadRequestException;
	public Recipient readById(String id) throws BadRequestException;
	public void uploadImages(String id, MultipartFile multipartFile) throws IOException, BadRequestException;
	public void delete(String id) throws BadRequestException, IOException;
	public boolean exists(String id);
}
