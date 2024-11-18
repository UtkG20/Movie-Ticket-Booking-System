package com.example.MovieBookingSystem.Repository;

import com.example.MovieBookingSystem.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieDao extends JpaRepository<Movie,Integer> {

    Movie findByMovieTitle(String title);
}
