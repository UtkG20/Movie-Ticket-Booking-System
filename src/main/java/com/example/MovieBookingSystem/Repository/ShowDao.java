package com.example.MovieBookingSystem.Repository;

import com.example.MovieBookingSystem.Models.Movie;
import com.example.MovieBookingSystem.Models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowDao extends JpaRepository<Show,Integer> {

    List<Show> findByMovie(Movie movie);
}
