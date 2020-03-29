package com.tbf.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tbf.repository.ListingRepository;
import com.tbf.exception.ResourceNotFoundException;
import com.tbf.model.Listing;

@RestController
@CrossOrigin(origins = "http://localhost:4200")	
@RequestMapping("/api/test")
public class ListingController {

	@Autowired
	private ListingRepository listingRepository;
	
	@GetMapping("/listings")
	public List<Listing> getAllListings(){
		return listingRepository.findAll();
	}
	
	@GetMapping("/listings/{id}")
	public ResponseEntity<Listing> getListingByID(@PathVariable(value="id") Long listingId)
		throws ResourceNotFoundException{
		Listing listing = listingRepository.findById(listingId)
				.orElseThrow(() -> new ResourceNotFoundException("Listing not found for this id :: " + listingId));
		return ResponseEntity.ok().body(listing);
	}
	
	@PostMapping("/listings")
	public Listing createListing(@Valid @RequestBody Listing listing) {
		return listingRepository.save(listing);
	}
	
	@PutMapping("/listings/{id}")
	public ResponseEntity<Listing> updateListing(@PathVariable(value="id") Long listingId,
			@Valid @RequestBody Listing listingDetails) throws ResourceNotFoundException{
		
		Listing listing = listingRepository.findById(listingId)
				.orElseThrow(() -> new ResourceNotFoundException("Listing not found for this id :: " + listingId));
		listing.setSellerId(listingDetails.getSellerId());
		listing.setTitle(listingDetails.getTitle());
		listing.setCategory(listingDetails.getCategory());
		listing.setQuality(listingDetails.getQuality());
		listing.setDescription(listingDetails.getDescription());
		listing.setPrice(listingDetails.getPrice());
		final Listing updatedListing = listingRepository.save(listing);
		return ResponseEntity.ok(updatedListing);
	}
	
	@DeleteMapping("/listings{id}")
	public Map<String, Boolean> deleteListing(@PathVariable(value="id") Long listingId)
			throws ResourceNotFoundException{
		Listing listing = listingRepository.findById(listingId)
				.orElseThrow(() -> new ResourceNotFoundException("Listing not found for this id :: " + listingId));
		listingRepository.delete(listing);
		Map<String,Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
