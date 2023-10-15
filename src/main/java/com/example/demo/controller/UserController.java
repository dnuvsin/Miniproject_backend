package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Users;
import com.example.demo.repository.UserRepository;

@RestController
@CrossOrigin("*")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@GetMapping("/user")
	public ResponseEntity<Object> getUser() {
		try {
			List<Users> users = userRepository.findAll();
			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Intermal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<Object> getUserDetail(@PathVariable Integer userId){

		try {
			Optional<Users> user = userRepository.findById(userId);
			if(user.isPresent()) {
				return new ResponseEntity<>(user, HttpStatus.OK);
			}
			return new ResponseEntity<>("User Not Found", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/addUser")
	public ResponseEntity<Object> addEmployee(@RequestBody Users body) {
		try {

			Users user = userRepository.save(body);

			return new ResponseEntity<>(user, HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/testLogin")
	public ResponseEntity<Object> userLogin(@RequestBody Users body) {
		try {
			Optional<Users> userOptional = userRepository.findByUserEmail(body.getUserEmail());
			
			if (userOptional.isPresent() && userOptional.get().getUserPassword().equals(body.getUserPassword())) {
				userOptional.get().setUserPassword(null);
				
				return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Invalid", HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}


	@PutMapping("/user/{userId}")
	public ResponseEntity<Object> updateUserDetail(@PathVariable Integer userId, @RequestBody Users body){

		try {
			Optional<Users> user = userRepository.findById(userId);

			if(user.isPresent()) {

				Users userEdit = user.get();

				userEdit.setUserName(body.getUserName());
				userEdit.setUserEmail(body.getUserEmail());
				userEdit.setUserPhone(body.getUserPhone());
				userEdit.setUserPassword(body.getUserPassword());

				userRepository.save(userEdit);

				return new ResponseEntity<>(userEdit, HttpStatus.OK);
			}

			return new ResponseEntity<>("User Not Found", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delUser/{userId}")
	public ResponseEntity<Object> deleteUser(@PathVariable Integer userId) {
		try {
			Optional<Users> user = userRepository.findById(userId);

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
