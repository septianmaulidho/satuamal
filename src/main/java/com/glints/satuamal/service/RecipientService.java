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
	public Recipient update(Integer id, RecipientPayload recipientPayload) throws BadRequestException;
	public Recipient readById(Integer id) throws BadRequestException;
	public void uploadImages(Integer id, MultipartFile multipartFile) throws IOException, BadRequestException;
	public void delete(Integer id) throws BadRequestException, IOException;
	public boolean exists(Integer id);
}
