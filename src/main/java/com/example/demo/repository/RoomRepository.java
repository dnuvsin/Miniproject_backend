package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Rooms;

@Repository
public interface RoomRepository extends JpaRepository<Rooms, Integer>{

}
