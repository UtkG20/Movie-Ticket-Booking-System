package com.example.MovieBookingSystem.Repository;

import com.example.MovieBookingSystem.Models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingDao extends JpaRepository<Booking,Integer> {
}
