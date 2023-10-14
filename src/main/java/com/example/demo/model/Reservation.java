package com.example.demo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private int reservationId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "roomId")
    private Rooms room;


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

	public Reservation(int reservationId, Users user, Rooms room, LocalDate reserveDate, LocalDate checkInDate,
			LocalDate checkOutDate) {
		super();
		this.reservationId = reservationId;
		this.user = user;
		this.room = room;
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

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Rooms getRoom() {
		return room;
	}

	public void setRoom(Rooms room) {
		this.room = room;
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
