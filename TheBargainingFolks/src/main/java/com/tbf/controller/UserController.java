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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tbf.repository.UserRepository;
import com.tbf.exception.ResourceNotFoundException;
import com.tbf.model.LoginForm;
import com.tbf.model.User;

@RestController
@CrossOrigin(origins = "http://localhost:4200")	
@RequestMapping("/api/test")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserByID(@PathVariable(value="id") Long userId)
		throws ResourceNotFoundException{
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
		return ResponseEntity.ok().body(user);
	}
	
	@PostMapping("/users/login")
	public User getUserForLogin(@RequestBody LoginForm log) {
		return userRepository.checkValidLogin(log.getEmail(), log.getPassword());
	}
	
	/*
	System.out.println("Checking Login for: " + log.getEmail() + " " + log.getPassword());
	User checkUser = userRepository.checkValidLogin(log.getEmail(), log.getPassword());
	
	if (checkUser == null) {
		System.out.println("Not a valid login");
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	System.out.println("Valid login found");
	return new ResponseEntity<User>(checkUser, HttpStatus.OK);
	*/
	
	@PostMapping("/users")
	public User createUser(@Valid @RequestBody User user) {
		return userRepository.save(user);
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value="id") Long userId,
			@Valid @RequestBody User userDetails) throws ResourceNotFoundException{
		System.out.println("Updating a user here!!!!!");
		//User userDetails = (User)userJson;
		
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
		user.setUsername(userDetails.getUsername());
		user.setPassword(userDetails.getPassword());
		user.setEmail(userDetails.getEmail());
		user.setfName(userDetails.getfName());
		user.setlName(userDetails.getlName());
		final User updatedUser = userRepository.save(user);
		return ResponseEntity.ok(updatedUser);
	}
	
	@DeleteMapping("/users/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value="id") Long userId)
			throws ResourceNotFoundException{
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
		userRepository.delete(user);
		Map<String,Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
		
	}
	
	
	
	
}
