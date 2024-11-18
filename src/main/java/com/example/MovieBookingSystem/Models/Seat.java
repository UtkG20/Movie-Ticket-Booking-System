package com.example.MovieBookingSystem.Models;

import com.example.MovieBookingSystem.Enums.SeatCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seatId;

    @Version  // This enables optimistic locking
    private int version;

    @Enumerated(EnumType.STRING)
    private SeatCategory seatCategory;

    @ManyToOne
    @JoinColumn(name = "showId")
    @JsonIgnore
    private Show show;

    private int price;

    private boolean isAvailable;

    public Seat(SeatCategory seatCategory, Show show , int price){
        this.seatCategory = seatCategory;
        this.show = show;
        this.price = price;
        this.isAvailable = true;
    }


}
