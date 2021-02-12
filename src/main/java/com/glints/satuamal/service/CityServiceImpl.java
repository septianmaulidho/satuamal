package com.glints.satuamal.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glints.satuamal.exception.BadRequestIdException;
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
	public City update(Integer id, CityPayload cityPayload) throws BadRequestIdException {
		City city = cityRepo.findById(id).orElseThrow(() -> new BadRequestIdException("City with id: " + id + " not found!"));
		city.setCityName(cityPayload.getCityName());
		city.setUpdatedTime(new Date());
		city = cityRepo.save(city);
		return city;
	}

	@Override
	public String delete(Integer id) throws BadRequestIdException {
		City city = cityRepo.findById(id).orElseThrow(() -> new BadRequestIdException("City with id: " + id + " not found!"));
		cityRepo.deleteById(city.getId());
		return city.getCityName() + " City has been deleted!";
	}

	@Override
	public City readById(Integer id) throws BadRequestIdException {
		City city = cityRepo.findById(id).orElseThrow(() -> new BadRequestIdException("City with id: " + id + " not found!"));
		return city;
	}
	
}
