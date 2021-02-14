package com.testing.LastProject.Controller;

//import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.testing.LastProject.ErrorHandler.ResourceNotFoundException;
import com.testing.LastProject.Repository.TransactionRepository;
//import com.testing.LastProject.Repository.UserRepository;
import com.testing.LastProject.Service.FileStorageService;
import com.testing.LastProject.model.Transaction;
//import com.testing.LastProject.model.User;
//import com.testing.LastProject.payload.TransactionPayload;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	FileStorageService fileStorageService;
	
//	@Autowired
//	UserRepository userRepository;
	
//	@Autowired
//	DonationRepository donationRepository;
	
	@PostMapping
	public ResponseEntity<Transaction> create(
//			@RequestParam("user") User user,
//			@RequestParam("donation") Donation donation,
			@RequestParam("photo")MultipartFile photo){
		
		String fileName = fileStorageService.storeFile(photo);
		Transaction transaction = new Transaction();
		String pictureUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
										.path("transaction/")
										.path(fileName)
										.toUriString();
		
//		transaction.setUser(getUser());
//		transaction.setDonation(getDonation());
		transaction.setPaymentPhotoUrl(pictureUrl);
		transaction = transactionRepository.save(transaction);
		
		return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
	}
	
	@GetMapping("/transaction/{fileName}")
		ResponseEntity<Resource> downloadTransactionFile(@PathVariable String fileName){
		Resource resource = fileStorageService.downloadFile(fileName);
		MediaType contentType = MediaType.IMAGE_JPEG;
		
		return ResponseEntity.ok()
				.contentType(contentType)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment:fileName=" + resource.getFilename())
				.body(resource);
	}
	
	@GetMapping
	public List<Transaction> findAll(){
		return this.transactionRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Transaction> findBydId(@PathVariable("id") String id)
	throws ResourceNotFoundException {
		Transaction transaction = transactionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("transaction you're looking for not found"));
		return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Transaction> update(
			@PathVariable("id") String id,
//			@RequestParam("user") User user,
//			@RequestParam("donation") Donation donation,
			@RequestParam("photo") MultipartFile photo) throws ResourceNotFoundException{
		
		Transaction transaction = transactionRepository.findById(id)
							.orElseThrow(() -> new ResourceNotFoundException("transaction you're looking for not found"));
		
		String fileName = fileStorageService.storeFile(photo);
		
		String pictureUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
												.path("transaction/")
												.path(fileName)
												.toUriString();
		
		transaction.setPaymentPhotoUrl(pictureUrl);
		transaction = transactionRepository.save(transaction);
		
		return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
	}
}
