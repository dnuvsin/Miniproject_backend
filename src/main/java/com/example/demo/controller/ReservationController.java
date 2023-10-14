package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Reservation;
import com.example.demo.model.Role;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.UserRepository;

@RestController
@CrossOrigin("*")
public class ReservationController {

	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoomRepository roomRepository;

	@PostMapping("/reservations")
	public ResponseEntity<Object> ReserveRoom(@RequestBody Reservation body) {
		try {
			Reservation reservation = reservationRepository.save(body);
			return new ResponseEntity<>(reservation, HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


}
