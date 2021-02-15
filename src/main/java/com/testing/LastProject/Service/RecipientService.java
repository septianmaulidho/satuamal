package com.testing.LastProject.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.testing.LastProject.ErrorHandler.BadRequestException;
import com.testing.LastProject.model.Recipient;
import com.testing.LastProject.payload.RecipientPayload;

public interface RecipientService {
	public List<Recipient> read();
	public Recipient create(UUID userId, RecipientPayload recipientPayload) throws BadRequestException;
	public Recipient update(UUID userId, String id, RecipientPayload recipientPayload) throws BadRequestException;
	public Recipient readById(String id) throws BadRequestException;
	public void uploadImages(String id, MultipartFile multipartFile) throws IOException, BadRequestException;
	public void delete(String id) throws BadRequestException, IOException;
	public boolean exists(String id);
}
