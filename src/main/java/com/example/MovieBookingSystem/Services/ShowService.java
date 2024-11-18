package com.example.MovieBookingSystem.Services;

import com.example.MovieBookingSystem.Enums.SeatCategory;
import com.example.MovieBookingSystem.Models.Movie;
import com.example.MovieBookingSystem.Models.Seat;
import com.example.MovieBookingSystem.Models.Show;
import com.example.MovieBookingSystem.Repository.ShowDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

    @Autowired
    ShowDao showDao;

    public Show getShowById(int showId){
        Optional<Show> show = this.showDao.findById(showId);
        if(show.isPresent()){
            return show.get();
        }
        return null;
    }

    public int addShow(Movie movie, LocalDateTime showTime, int noOfSeats){
        try{
                Show newShow = new Show(movie, showTime);
                for (int i = 0; i < noOfSeats; i++) {
                    Seat seat = new Seat(SeatCategory.RECLINER, newShow, 400);
                    newShow.getSeats().add(seat);
                }
                newShow.setAvailableSeatCount(noOfSeats);
                Show savedShow = this.showDao.save(newShow);
                return savedShow.getShowId();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return 0;
        }

    }

    public List<Show> getMovieShows(Movie movie) throws RuntimeException{
        try {
            List<Show> shows = this.showDao.findByMovie(movie);
            return shows;
        }catch (Exception e){
            throw new RuntimeException("Exception occurred while fetching show list"+e.getMessage());
        }
    }
}
