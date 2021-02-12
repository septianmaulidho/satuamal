package com.glints.satuamal.service;

import java.util.List;

import com.glints.satuamal.exception.BadRequestIdException;
import com.glints.satuamal.model.City;
import com.glints.satuamal.payload.CityPayload;

public interface CityService {
	public List<City> read();
	public City create(CityPayload cityPayload);
	public City update(Integer id, CityPayload cityPayload) throws BadRequestIdException;
	public String delete(Integer id) throws BadRequestIdException;
	public City readById(Integer id) throws BadRequestIdException;
}
