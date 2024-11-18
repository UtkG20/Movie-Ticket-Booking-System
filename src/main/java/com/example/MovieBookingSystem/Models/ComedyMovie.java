package com.example.MovieBookingSystem.Models;

import com.example.MovieBookingSystem.DTO.MovieDetailsDTO;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
public class ComedyMovie extends Movie{

    private String comedian;

    public ComedyMovie(String movieTitle, String actionHero){
        super(movieTitle);
        this.comedian = actionHero;
    }

    public ComedyMovie(String movieTitle, List<Show> shows){
        super(movieTitle,shows);
    }

    @Override
    public MovieDetailsDTO showMovieDetails() {
        return new MovieDetailsDTO(this.getMovieTitle(), "COMEDY");
    }
}
