package com.example.MovieBookingSystem.RestController;

import com.example.MovieBookingSystem.Services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("booking")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @GetMapping("getMovieShows")
    public ResponseEntity<?> getMovieShows(@RequestParam int movieId){
        return bookingService.getMovieShows(movieId);
    }

    @PostMapping("createBooking")
    public ResponseEntity<?> createBooking(@RequestParam int userId, @RequestParam int showId, @RequestParam int seatCount){
        return this.bookingService.createBooking(userId,showId,seatCount);
    }

    @PostMapping("cancel")
    public ResponseEntity<?> cancelBooking(@RequestParam int bookingId){
        return this.bookingService.cancelBooking(bookingId);
    }

}
