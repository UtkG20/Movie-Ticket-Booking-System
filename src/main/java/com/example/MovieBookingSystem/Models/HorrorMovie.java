package com.example.MovieBookingSystem.Models;

import com.example.MovieBookingSystem.DTO.HorrorMovieDetailDTO;
import com.example.MovieBookingSystem.DTO.MovieDetailsDTO;
import com.example.MovieBookingSystem.Enums.ScaryLevel;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
public class HorrorMovie extends Movie{

    @Enumerated(EnumType.STRING)
    private ScaryLevel scaryLevel;

    public HorrorMovie( String movieTitle, ScaryLevel scaryLevel) {
        super(movieTitle);
        this.scaryLevel = scaryLevel;
    }

    public HorrorMovie(String movieTitle, List<Show> shows){
        super(movieTitle,shows);
    }

    @Override
    public MovieDetailsDTO showMovieDetails() {
        return new HorrorMovieDetailDTO(this.getMovieTitle(), "HORROR",this.scaryLevel);
    }

}
