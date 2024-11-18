package com.example.MovieBookingSystem.Repository;

import com.example.MovieBookingSystem.Models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatDao extends JpaRepository<Seat,Integer> {

}
