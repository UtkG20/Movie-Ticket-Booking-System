package com.example.MovieBookingSystem.Services;

import com.example.MovieBookingSystem.DTO.ComedyMovieDetailDTO;
import com.example.MovieBookingSystem.DTO.HorrorMovieDetailDTO;
import com.example.MovieBookingSystem.DTO.MovieDetailsDTO;
import com.example.MovieBookingSystem.DTO.ShowDTO;
import com.example.MovieBookingSystem.Models.ComedyMovie;
import com.example.MovieBookingSystem.Models.HorrorMovie;
import com.example.MovieBookingSystem.Models.Movie;
import com.example.MovieBookingSystem.Repository.MovieDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieDao movieDao;

    @Autowired
    private ShowService showService;


    public Optional<Movie> getMovieById(int movieId){
        Optional<Movie> movie = movieDao.findById(movieId);
        if(movie.isPresent()){
            return movie;
        }
        return Optional.empty();
    }

    public ResponseEntity<?> addMovie(MovieDetailsDTO movieDetailsDTO){

        System.out.println(movieDetailsDTO.toString());
        try{
            Movie movie;
            if (movieDetailsDTO instanceof HorrorMovieDetailDTO) {
                movie = new HorrorMovie();
                HorrorMovieDetailDTO horrorMovieDetailDTO = (HorrorMovieDetailDTO) movieDetailsDTO;
                movie = this.movieDao.save(new HorrorMovie(horrorMovieDetailDTO.getMovieTitle(),horrorMovieDetailDTO.getScaryLevel()));
            }else if(movieDetailsDTO instanceof ComedyMovieDetailDTO){
                movie = new ComedyMovie();
                ComedyMovieDetailDTO comedyMovieDetailDTO = (ComedyMovieDetailDTO) movieDetailsDTO;
                movie = this.movieDao.save(new ComedyMovie(comedyMovieDetailDTO.getMovieTitle(),comedyMovieDetailDTO.getComedian()));
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("It is not a valid movie details");
            }

            return ResponseEntity.status(HttpStatus.OK).body("Movie added successfully with movieId: "+ movie.getMovieId() );
        }catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Movie title already exist");
        }
    }

    public ResponseEntity<?> getListOfMovies(){
        try {
            List<Movie> movies = movieDao.findAll();
            if(movies.isEmpty()){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Movies List is empty");
            }
            return ResponseEntity.status(HttpStatus.OK).body(movies);
        }catch (DataAccessException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error accessing the DB"+ e);
        }
    }

    public ResponseEntity<?> addShow(ShowDTO showDTO){
        Optional<Movie> movie = this.movieDao.findById(showDTO.getMovieId());
        if(!movie.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No movie present with this movieId");
        }
        int result = this.showService.addShow(movie.get(),showDTO.getShowTime(),showDTO.getNoOfSeats());
        if(result>0){
            return ResponseEntity.status(HttpStatus.CREATED).body("Show added successfully with showId: "+ result);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Issue occured in adding show");
    }
}
