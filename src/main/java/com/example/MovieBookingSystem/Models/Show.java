package com.example.MovieBookingSystem.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int showId;

    @Version  // Optimistic Locking Version field
    private int version;

    private LocalDateTime showTime;

    @ManyToOne
    @JoinColumn(name = "movieId")
    private Movie movie;

    @JsonIgnore
    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    private List<Seat> seats;

    private int availableSeatCount;

    public Show(Movie movie, LocalDateTime showTime){
        this.movie = movie;
        this.showTime = showTime;
        this.seats = new ArrayList<>();
    }

}
