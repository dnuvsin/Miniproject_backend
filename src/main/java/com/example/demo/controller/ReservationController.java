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

import com.example.demo.model.Reservation;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ReservationService;

@RestController
@CrossOrigin("*")
public class ReservationController {

    @Autowired
    ReservationRepository reservationRepository;
    
    @Autowired
    ReservationService reservationService; // Inject the service

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoomRepository roomRepository;
    
	@GetMapping("/reservationsHistory")
	public ResponseEntity<Object> getResrve() {
		try {
			List<Reservation> reservations = reservationRepository.findAll();
			return new ResponseEntity<>(reservations, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Intermal server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @PostMapping("/reservations")
    public ResponseEntity<Object> reserveRoom(@RequestBody Reservation reservation) {
        try {
            Reservation newReservation = reservationService.createReservation(reservation);
            
            return new ResponseEntity<>(newReservation, HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/reservationsHistory/{userId}")
    public ResponseEntity<Object> getReservationsHistory(@PathVariable Integer userId) {
        try {
            List<Reservation> history = reservationRepository.findByUserId(userId);
            if (!history.isEmpty()) {
                return new ResponseEntity<>(history, HttpStatus.OK);
            }
            return new ResponseEntity<>("Reservations Not Found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
	@PutMapping("/reservationsHistory/{reservationId}")
	public ResponseEntity<Object> updateReserve(@PathVariable Integer reservationId, @RequestBody Reservation body) {

		try {
			Optional<Reservation> reserveOptional = reservationRepository.findById(reservationId);

			if (reserveOptional.isPresent()) {

				Reservation reservationEdit = reserveOptional.get();

				reservationEdit.setCheckInDate(body.getCheckInDate());
				reservationEdit.setCheckOutDate(body.getCheckOutDate());

				reservationRepository.save(reservationEdit);

				return new ResponseEntity<>(reservationEdit, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("employee not found", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("INTERNAL SERVER ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@DeleteMapping("/reservationsHistory/{reservationId}")
	public ResponseEntity<Object> deleteReserve(@PathVariable Integer reservationId) {
		try {
			Optional<Reservation> reserveOptional = reservationRepository.findById(reservationId);

			if (reserveOptional.isPresent()) {
				reservationRepository.delete(reserveOptional.get());
				return new ResponseEntity<>("Delete Success", HttpStatus.OK);

			} else {
				return new ResponseEntity<>("reserve not found", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("INTERNAL SERVER ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}


	
