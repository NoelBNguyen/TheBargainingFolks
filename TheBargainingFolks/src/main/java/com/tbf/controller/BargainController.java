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

import com.tbf.repository.BargainRepository;
import com.tbf.exception.ResourceNotFoundException;
import com.tbf.model.Bargain;

@RestController //
@CrossOrigin(origins = "http://localhost:4200")	
@RequestMapping("/api/test")
public class BargainController {
	
	@Autowired
	private BargainRepository bargainRepository;
	
	@GetMapping("/bargain")
	public List<Bargain> getAllBargains(){
		return bargainRepository.findAll();
	}
	
	@GetMapping("/bargain/{id}")
	public ResponseEntity<Bargain> getBargainByID(@PathVariable(value="id") Long bargainId)
		throws ResourceNotFoundException{
		Bargain bargain = bargainRepository.findById(bargainId)
				.orElseThrow(() -> new ResourceNotFoundException("Bargain not found for this id :: " + bargainId));
		return ResponseEntity.ok().body(bargain);
	}
	
	@PostMapping("/bargain")
	public Bargain createBargain(@Valid @RequestBody Bargain bargain) {
		return bargainRepository.save(bargain);
	}
	
	@PutMapping("/bargain/{id}")
	public ResponseEntity<Bargain> updateBargain(@PathVariable(value="id") Long bargainId,
			@Valid @RequestBody Bargain bargainDetails) throws ResourceNotFoundException{
		
		Bargain bargain = bargainRepository.findById(bargainId)
				.orElseThrow(() -> new ResourceNotFoundException("Bargain not found for this id :: " + bargainId));
		bargain.setBuyerId(bargainDetails.getBuyerId());
		bargain.setSellerId(bargainDetails.getSellerId());
		bargain.setValue(bargainDetails.getValue());
		bargain.setDateTime(bargainDetails.getDateTime());
		final Bargain updatedBargain = bargainRepository.save(bargain);
		return ResponseEntity.ok(updatedBargain);
	}
	
	@DeleteMapping("/bargain")
	public Map<String, Boolean> deleteBargain(@PathVariable(value="id") Long bargainId)
			throws ResourceNotFoundException{
		Bargain bargain = bargainRepository.findById(bargainId)
				.orElseThrow(() -> new ResourceNotFoundException("Bargain not found for this id :: " + bargainId));
		bargainRepository.delete(bargain);
		Map<String,Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
		
	}
	
	
	
	
}
