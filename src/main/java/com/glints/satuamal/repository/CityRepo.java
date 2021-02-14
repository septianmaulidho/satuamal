package com.glints.satuamal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glints.satuamal.model.City;

public interface CityRepo extends JpaRepository<City, String> {

}
