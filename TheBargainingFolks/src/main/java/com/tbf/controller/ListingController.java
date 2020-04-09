package com.tbf.controller;

import java.util.ArrayList;
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
import com.tbf.model.UserIdData;

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
	
	@GetMapping("/listings/active")
	public List<Listing> getAllActiveListings(){
		return listingRepository.findByStatusOrderByUploadTimeAsc("active");
	}
	
	@GetMapping("/listings/{id}")
	public ResponseEntity<Listing> getListingByID(@PathVariable(value="id") Long listingId)
		throws ResourceNotFoundException{
		Listing listing = listingRepository.findById(listingId)
				.orElseThrow(() -> new ResourceNotFoundException("Listing not found for this id :: " + listingId));
		return ResponseEntity.ok().body(listing);
	}
	
	
	@GetMapping("/listings/search/{subStr}")
	public List<Listing> getListingBySearch(@PathVariable(value="subStr") String search) throws ResourceNotFoundException {
		
		List<Listing> allResults = listingRepository.findByStatusOrderByUploadTimeAsc("active");
		List<Listing> searchResults = new ArrayList<Listing>();
		
		for(Listing result : allResults) {
			if (result.getTitle().contains(search)) {
				searchResults.add(result);
			}
		}
		
		return searchResults;
	}
			
	
	@PostMapping("/listings")
	public Listing createListing(@Valid @RequestBody Listing listing) {
		return listingRepository.save(listing);
	}
	
	
	@PostMapping("/listings/selfactive")
	public List<Listing> getOwnActiveListings(@RequestBody UserIdData data){
		return listingRepository.findBySellerIdAndStatusOrderByUploadTimeAsc(data.getIdData(), "active");
	}
	
	@PostMapping("/listings/selfsold")
	public List<Listing> getOwnSoldListings(@RequestBody UserIdData data){
		return listingRepository.findBySellerIdAndStatusOrderByUploadTimeAsc(data.getIdData(), "sold");
	}
	
	@PostMapping("/listings/selfbought")
	public List<Listing> getOwnBoughtListings(@RequestBody UserIdData data){
		return listingRepository.findByBuyerIdAndStatusOrderByUploadTimeAsc(data.getIdData(), "sold");
	}
	
	
	@PutMapping("listings/purchase/{id}")
	public ResponseEntity<Listing> purchaseListing(@PathVariable(value="id") Long listingId,
			@RequestBody UserIdData buyObj) throws ResourceNotFoundException{
		Listing listing = listingRepository.findById(listingId)
				.orElseThrow(() -> new ResourceNotFoundException("Listing not found for this id :: " + listingId));
		listing.setStatus("sold");
		System.out.println(buyObj.getIdData());
		listing.setBuyerId(buyObj.getIdData());
		//listing.setResolvedTime is called automatically
		final Listing updatedListing = listingRepository.save(listing);
		return ResponseEntity.ok(updatedListing);
	}
	
	@PutMapping("listings/cancel/{id}")
	public ResponseEntity<Listing> cancelListing(@PathVariable(value="id") Long listingId,
			@RequestBody UserIdData sellData) throws ResourceNotFoundException{
		Listing listing = listingRepository.findById(listingId)
				.orElseThrow(() -> new ResourceNotFoundException("Listing not found for this id :: " + listingId));
		listing.setStatus("cancelled");
		//listing.setBuyerId(sellData.getIdData());
		//listing.setResolvedTime is called automatically
		final Listing updatedListing = listingRepository.save(listing);
		return ResponseEntity.ok(updatedListing);
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
		listing.setImg(listingDetails.getImg());
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
