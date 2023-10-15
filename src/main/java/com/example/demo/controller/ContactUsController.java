package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ContactUs;
import com.example.demo.repository.ContactUsRepository;

@RestController
@CrossOrigin("*")
public class ContactUsController {
	
	@Autowired
	ContactUsRepository contactUsRepository;
	
	@PostMapping("/contact")
	public ResponseEntity<Object> addContact(@RequestBody ContactUs body) {
		try {

			ContactUs contactUs = contactUsRepository.save(body);

			return new ResponseEntity<>(contactUs, HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
