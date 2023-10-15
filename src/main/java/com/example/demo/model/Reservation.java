package com.example.demo.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private int reservationId;
    
    @Column(name = "userId")
    private Integer userId;
    
    @Column(name = "roomId")
    private Integer roomId;

    @Column(name = "reserveDate")
    private LocalDate reserveDate;

    @Column(name = "checkInDate")
    private LocalDate checkInDate;

    @Column(name = "checkOutDate")
    private LocalDate checkOutDate;


	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Reservation(int reservationId, Integer userId, Integer roomId, LocalDate reserveDate, LocalDate checkInDate,
			LocalDate checkOutDate) {
		super();
		this.reservationId = reservationId;
		this.userId = userId;
		this.roomId = roomId;
		this.reserveDate = reserveDate;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
	}


	public int getReservationId() {
		return reservationId;
	}


	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public Integer getRoomId() {
		return roomId;
	}


	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}


	public LocalDate getReserveDate() {
		return reserveDate;
	}


	public void setReserveDate(LocalDate reserveDate) {
		this.reserveDate = reserveDate;
	}


	public LocalDate getCheckInDate() {
		return checkInDate;
	}


	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}


	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}


	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	

}
