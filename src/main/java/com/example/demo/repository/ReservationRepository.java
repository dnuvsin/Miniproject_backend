package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
	
	List<Reservation> findByUserId(Integer userId);

}
