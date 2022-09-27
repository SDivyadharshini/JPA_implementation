package com.example.jdbc.project_JPA.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jdbc.project_JPA.Bean.HotelRoom;

public interface RoomRepository extends JpaRepository<HotelRoom, String> {

}
