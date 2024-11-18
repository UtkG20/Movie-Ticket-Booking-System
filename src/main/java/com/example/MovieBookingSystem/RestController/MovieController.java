package com.example.MovieBookingSystem.RestController;

import com.example.MovieBookingSystem.DTO.MovieDetailsDTO;
import com.example.MovieBookingSystem.DTO.ShowDTO;
import com.example.MovieBookingSystem.Services.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("addMovie")
    public ResponseEntity<?> addMovie(@Valid @RequestBody MovieDetailsDTO movieDetailsDTO){
        return this.movieService.addMovie(movieDetailsDTO);
    }

    @GetMapping("getAll")
    public ResponseEntity<?> getMovieList(){
        return this.movieService.getListOfMovies();
    }

    @PostMapping("addShow")
    public ResponseEntity<?> addMovieShow(@Valid @RequestBody ShowDTO showDTO){
        return this.movieService.addShow(showDTO);
    }
}
