package com.example.MovieBookingSystem.Models;

import com.example.MovieBookingSystem.DTO.MovieDetailsDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@NoArgsConstructor
public abstract class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int movieId;

    @NonNull
    @Column(unique = true)
    private String movieTitle;

    @JsonIgnore
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Show> shows;

    public Movie(String movieTitle, List<Show> shows){
        this.movieTitle = movieTitle;
        this.shows = shows;
    }

    public Movie(String movieTitle){
        this.movieTitle = movieTitle;
        this.shows = new ArrayList<>();
    }

    public abstract MovieDetailsDTO showMovieDetails();
}
