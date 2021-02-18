package com.testing.LastProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.testing.LastProject.ErrorHandler.ResourceNotFoundException;
import com.testing.LastProject.Repository.DonationRepo;
import com.testing.LastProject.Repository.RecipientRepository;
import com.testing.LastProject.Repository.UserRepository;
import com.testing.LastProject.Service.FileStorageService;
import com.testing.LastProject.model.Donation;
import com.testing.LastProject.model.Recipient;
import com.testing.LastProject.model.User;


import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;




@RestController
@RequestMapping("/api/donation")
public class DonationController {
	@Autowired
	private DonationRepo donationRepo;
	@Autowired
	private RecipientRepository recipientRepo;
	@Autowired
	private UserRepository userRepo;
	
	private FileStorageService fileStorageService;

	
	public DonationController(FileStorageService fileStorageService) {
		this.fileStorageService = fileStorageService;
	}

	@Transactional
	@PostMapping("/addDonation")
	public ResponseEntity<Donation> create(
			@RequestHeader(value="authorization") String token,
			@RequestParam("accepted_Date") 	@DateTimeFormat(pattern = "yyyy-MM-dd") Date accepted_Date,
			@RequestParam("given_Date")	@DateTimeFormat(pattern = "yyyy-MM-dd")Date given_Date,
			@RequestParam("photo")MultipartFile photo,
			@RequestParam("user_id") UUID user_id,
			@RequestParam("recipient_id") String recipient_id
			){
		
		/** Get recipient_id and user_id*/
		Recipient recipient = recipientRepo.findById(recipient_id).get();
	
		User userId = userRepo.findById(user_id).orElse(null);
		
		System.out.println(recipient);
		
		
		/** change file into URL*/
		String fileName = fileStorageService.storeFile(photo);
		String pictureUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
						.path("/api/donation/")
						.path(fileName)
						.toUriString();
		
		/** set the created value*/
		Donation donation = new Donation();
		donation.setAccepted_Date(accepted_Date);
		donation.setGiven_Date(given_Date);
		donation.setPhoto(pictureUrl);
		donation.setRecipient(recipient);
		donation.setUser(userId);
		
		donation = donationRepo.save(donation);
		
		return new ResponseEntity<Donation>(donation,HttpStatus.OK);
	}
	
	/** Get Donation Data */
	@GetMapping("/getDonation")
	public List<Donation> getAll(){
		return this.donationRepo.findAll();
	}
	
	/** Get Donation by ID */
	@GetMapping("/getDonation/{id}")
	public ResponseEntity<Donation> getById(@PathVariable("id") String id)
	throws ResourceNotFoundException {
		Donation donation = donationRepo.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("We can't find the Id you are looking for"));
		return new ResponseEntity<Donation>(donation,HttpStatus.OK);
	}
	
	/** Edit Donation Data*/
	@PutMapping("/updateDonation/{id}")
	public ResponseEntity<Donation> update(
			@PathVariable("id") String id,
			@RequestParam("accepted_Date") 	@DateTimeFormat(pattern = "yyyy-MM-dd") Date accepted_Date,
			@RequestParam("given_Date")	@DateTimeFormat(pattern = "yyyy-MM-dd")Date given_Date,
			@RequestParam("photo")MultipartFile photo) throws ResourceNotFoundException{
		
		/** check donation id*/
		Donation donation = donationRepo.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("We can't find the Id you are looking for"));
		
		/** change file into URL*/
		String fileName = fileStorageService.storeFile(photo);
		
		String pictureUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
						.path("/donation/")
						.path(fileName)
						.toUriString();
		
		/** set the changing value*/
		donation.setAccepted_Date(accepted_Date);
		donation.setGiven_Date(given_Date);
		donation.setPhoto(pictureUrl);
		
		/** save changing value into repository*/
		donation = donationRepo.save(donation);
		
		return new ResponseEntity<Donation>(donation,HttpStatus.OK);
	}
	
	/** Get Donation (download) picture file */
	@GetMapping("/{fileName}")
	  ResponseEntity<Resource>downloadDonationFile(@PathVariable String fileName){
		Resource resource = fileStorageService.downloadFile(fileName);
		MediaType contentType = MediaType.IMAGE_JPEG;
		
		return ResponseEntity.ok()
				.contentType(contentType)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment:fileName="+resource.getFilename())
				.body(resource);
	}
	

}
