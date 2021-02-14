package com.glints.satuamal.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.glints.satuamal.exception.BadRequestIdException;
import com.glints.satuamal.model.Recipient;
import com.glints.satuamal.payload.RecipientPayload;

public interface RecipientService {
	public List<Recipient> read();
	public Recipient create(RecipientPayload recipientPayload) throws BadRequestIdException;
	public Recipient update(Integer id, RecipientPayload recipientPayload) throws BadRequestIdException;
	public void delete(Integer id) throws BadRequestIdException, IOException;
	public Recipient readById(Integer id) throws BadRequestIdException;
	public void uploadImages(Integer id, MultipartFile multipartFile) throws IOException;
	public boolean exists(Integer id);
}
