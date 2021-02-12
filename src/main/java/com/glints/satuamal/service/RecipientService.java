package com.glints.satuamal.service;

import java.util.List;

import com.glints.satuamal.exception.BadRequestIdException;
import com.glints.satuamal.model.Recipient;
import com.glints.satuamal.payload.RecipientPayload;

public interface RecipientService {
	public List<Recipient> read();
	public Recipient create(RecipientPayload recipientPayload) throws BadRequestIdException;
	public Recipient update(Integer id, RecipientPayload recipientPayload) throws BadRequestIdException;
	public String delete(Integer id) throws BadRequestIdException;
	public Recipient readById(Integer id) throws BadRequestIdException;
}
