package com.glints.satuamal.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glints.satuamal.exception.BadRequestException;
import com.glints.satuamal.model.City;
import com.glints.satuamal.payload.CityPayload;
import com.glints.satuamal.repository.CityRepo;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	CityRepo cityRepo;
	
	@Override
	public List<City> read() {
		List<City> cities = cityRepo.findAll();
		return cities;
	}

	@Override
	public City create(CityPayload cityPayload) {
		City city = new City(cityPayload.getCityName());
		city = cityRepo.save(city);
		return city;
	}

	@Override
	public City update(String id, CityPayload cityPayload) throws BadRequestException {
		City city = cityRepo.findById(id).orElseThrow(() -> new BadRequestException("City with id: " + id + " not found!"));
		city.setCityName(cityPayload.getCityName());
		city.setUpdatedTime(new Date());
		city = cityRepo.save(city);
		return city;
	}

	@Override
	public void delete(String id) throws BadRequestException {
		cityRepo.deleteById(id);
	}

	@Override
	public City readById(String id) throws BadRequestException {
		City city = cityRepo.findById(id).orElseThrow(() -> new BadRequestException("City with id: " + id + " not found!"));
		return city;
	}
	
}
