package com.example.MovieBookingSystem.Models;

import com.example.MovieBookingSystem.Enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;

    @ManyToOne
    @JoinColumn(name = "showId")
    private Show show;

    @ManyToMany
    @JoinTable(name = "bookingSeatMapping",
            joinColumns = @JoinColumn(name = "bookingId"),
            inverseJoinColumns = @JoinColumn(name = "seatId"))
    private List<Seat> seats;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    public Booking(Show show, List<Seat> seats, User user){
        this.show = show;
        this.seats = seats;
        this.user = user;
        this.bookingStatus = BookingStatus.BOOKED;
    }
}
