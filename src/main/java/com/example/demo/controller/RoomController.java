package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Rooms;
import com.example.demo.repository.RoomRepository;

@RestController
@CrossOrigin("*")
public class RoomController {
	
	@Autowired
	RoomRepository roomRepository;
	
	@GetMapping("/room")
	public ResponseEntity<Object> getUser() {
		try {
			List<Rooms> rooms = roomRepository.findAll();
			return new ResponseEntity<>(rooms, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Intermal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("room/{roomId}")
	public ResponseEntity<Object> getRoomDetail(@PathVariable Integer roomId){

		try {
			Optional<Rooms> room = roomRepository.findById(roomId);
			if(room.isPresent()) {
				return new ResponseEntity<>(room, HttpStatus.OK);
			}
			return new ResponseEntity<>("Room Not Found", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
