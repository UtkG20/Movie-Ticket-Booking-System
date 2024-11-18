package com.example.MovieBookingSystem.DTO;

import com.example.MovieBookingSystem.Enums.ScaryLevel;
import lombok.Getter;

@Getter
public class HorrorMovieDetailDTO extends MovieDetailsDTO{

    private ScaryLevel scaryLevel;

    public HorrorMovieDetailDTO(String movieName, String genre, ScaryLevel scaryLevel) {
        super(movieName, genre);
        this.scaryLevel = scaryLevel;
    }

}
