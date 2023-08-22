package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;

	@GetMapping("/user")
	public ResponseEntity<Object> getUser() {
		try {
			List<User> users = userRepository.findAll();
			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Intermal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/user/{user_id}")
	public ResponseEntity<Object> getUserDetail(@PathVariable Integer user_id){
		
		try {
			Optional<User> user = userRepository.findById(user_id);
			if(user.isPresent()) {
				return new ResponseEntity<>(user, HttpStatus.OK);
			}
			return new ResponseEntity<>("User Not Found", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/addUser")
	public ResponseEntity<Object> addEmployee(@RequestBody User body) {
		try {

			User user = userRepository.save(body);

			return new ResponseEntity<>(user, HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@PutMapping("/user/{user_id}")
	public ResponseEntity<Object> updateUserDetail(@PathVariable Integer user_id, @RequestBody User body){
		
		try {
			Optional<User> user = userRepository.findById(user_id);
			
			if(user.isPresent()) {
				
				User userEdit = user.get();
				
				userEdit.setUser_name(body.getUser_name());
				userEdit.setUser_email(body.getUser_email());
				userEdit.setUser_phone(body.getUser_phone());
				userEdit.setUser_password(body.getUser_password());
				
				userRepository.save(userEdit);
				
				return new ResponseEntity<>(userEdit, HttpStatus.OK);
			}
			
			return new ResponseEntity<>("User Not Found", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delUser/{user_id}")
	public ResponseEntity<Object> deleteUser(@PathVariable Integer user_id) {
		try {
			Optional<User> user = userRepository.findById(user_id);
			
			if(user.isPresent()) {
				
				userRepository.delete(user.get());
				
				return new ResponseEntity<>("Delete Success", HttpStatus.OK);
			}
			
			return new ResponseEntity<>("User Not Found", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
