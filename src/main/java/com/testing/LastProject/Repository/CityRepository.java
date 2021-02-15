package com.testing.LastProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testing.LastProject.model.City;

public interface CityRepository extends JpaRepository<City, String> {

	City findBycityName(String city); // Method to find city base on city name on request 
}
