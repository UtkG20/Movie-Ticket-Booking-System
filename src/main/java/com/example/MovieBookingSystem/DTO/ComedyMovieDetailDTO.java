package com.example.MovieBookingSystem.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComedyMovieDetailDTO extends MovieDetailsDTO{

    private String comedian;

    public ComedyMovieDetailDTO(String movieTitle, String genre, String comedian) {
        super(movieTitle, genre);
        this.comedian = comedian;
    }

}
