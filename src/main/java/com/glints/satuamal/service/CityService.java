package com.glints.satuamal.service;

import java.util.List;

import com.glints.satuamal.exception.BadRequestException;
import com.glints.satuamal.model.City;
import com.glints.satuamal.payload.CityPayload;

public interface CityService {
	public List<City> read();
	public City create(CityPayload cityPayload);
	public City update(String id, CityPayload cityPayload) throws BadRequestException;
	public void delete(String id) throws BadRequestException;
	public City readById(String id) throws BadRequestException;
}
