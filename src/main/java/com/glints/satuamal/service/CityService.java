package com.glints.satuamal.service;

import java.util.List;

import com.glints.satuamal.exception.BadRequestException;
import com.glints.satuamal.model.City;
import com.glints.satuamal.payload.CityPayload;

public interface CityService {
	public List<City> read();
	public City create(CityPayload cityPayload);
	public City update(Integer id, CityPayload cityPayload) throws BadRequestException;
	public void delete(Integer id) throws BadRequestException;
	public City readById(Integer id) throws BadRequestException;
}
