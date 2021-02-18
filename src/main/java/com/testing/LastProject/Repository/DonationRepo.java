package com.testing.LastProject.Repository;


import org.springframework.data.jpa.repository.JpaRepository;


import com.testing.LastProject.model.Donation;

public interface DonationRepo extends JpaRepository<Donation, String> {

	

}
