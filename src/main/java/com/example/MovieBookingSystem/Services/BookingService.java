package com.example.MovieBookingSystem.Services;

import com.example.MovieBookingSystem.Enums.BookingStatus;
import com.example.MovieBookingSystem.Models.Booking;
import com.example.MovieBookingSystem.Models.Seat;
import com.example.MovieBookingSystem.Models.Show;
import com.example.MovieBookingSystem.Models.User;
import com.example.MovieBookingSystem.Repository.BookingDao;
import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    BookingDao bookingDao;

    @Autowired
    MovieService movieService;

    @Autowired
    ShowService showService;

    @Autowired
    UserService userService;

    public ResponseEntity<?> getMovieShows(int movieId){
        try {
            if(this.movieService.getMovieById(movieId).isPresent()){
                System.out.println("1");
                List<Show> shows = this.showService.getMovieShows(this.movieService.getMovieById(movieId).get());
                return ResponseEntity.status(HttpStatus.FOUND).body(shows);
            }else {
                System.out.println("2");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No movie with this movieId");
            }
        }catch (Exception e){
            System.out.println("3");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Transactional
    public ResponseEntity<?> createBooking(int userId, int showId, int seatCount){
        try{
            Show show = this.showService.getShowById(showId);
            if (show == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("showId is invalid ");
            }

            // Prepare a list to store selected seats
            List<Seat> seats = new ArrayList<>();

            int count = seatCount;
            if(show.getAvailableSeatCount()>=seatCount) {
                // Find available seats
                for (Seat seat : show.getSeats()) {
                    if (seat.isAvailable()) {
                        seats.add(seat);
                        count--;
                        if (count == 0) break;
                    }
                }
            }else {
                // If not enough seats were found, return an error response
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Insufficient seats");
            }

            // Now, mark the selected seats as unavailable
            for(Seat seat: seats){
                seat.setAvailable(false);
            }
            show.setAvailableSeatCount(show.getAvailableSeatCount()-seatCount);

            User user = this.userService.getUserById(userId);
            if (user == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("userId is invalid ");
            Booking booking = new Booking(show, seats, user);
            Booking savedBooking = this.bookingDao.save(booking);

            return ResponseEntity.status(HttpStatus.OK).body("Booking created successfully with bookingId: " + savedBooking.getBookingId());
        }catch (OptimisticLockException ole) {
            // Handle optimistic locking failure (if seats were updated concurrently)
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Seats were booked by another user. Please try again.");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Issue in booking"+ e.getMessage());
        }
    }

    @Transactional
    public ResponseEntity<?> cancelBooking(int bookingId){
        try {
            Optional<Booking> booking = this.bookingDao.findById(bookingId);
            if (!booking.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("BookingId is invalid");
            }

            List<Seat> seats = booking.get().getSeats();
            for (Seat seat : seats) {
                seat.setAvailable(true);
            }
            booking.get().setBookingStatus(BookingStatus.CANCELLED);

            // calculate the new value of available seat count
            int newAvailableCount = booking.get().getShow().getAvailableSeatCount() + booking.get().getSeats().size();

            booking.get().getShow().setAvailableSeatCount(newAvailableCount);

            return ResponseEntity.status(HttpStatus.OK).body("Booking cancelled successfully");
        }catch (OptimisticLockException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict Occured while booking seats. Please try again.");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Issue in booking cancellation"+ e.getMessage());
        }
    }


}
