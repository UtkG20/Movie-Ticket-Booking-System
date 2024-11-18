package com.example.MovieBookingSystem.Services;

import com.example.MovieBookingSystem.DTO.UserDTO;
import com.example.MovieBookingSystem.Models.User;
import com.example.MovieBookingSystem.Repository.UserDao;
import jakarta.persistence.UniqueConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public ResponseEntity<?> createUser( UserDTO userDTO){
        try{
            if(userDao.findByEmail(userDTO.getEmail())!=null){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exist");
            }

            User savedUser = this.userDao.save(new User(userDTO.getUserName(),userDTO.getEmail()));
            return new ResponseEntity<>("User saved Successfully with UserId: "+ savedUser.getUserId(),HttpStatus.OK);
        }catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User already exist");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<?> getUserBookings(int userId){

        try{
            Optional<User> user = this.userDao.findById(userId);
            if (user.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(user.get().getBookings());
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public User getUserById(int userId){
        Optional<User> user = this.userDao.findById(userId);
        if(user.isPresent()) return user.get();
        return null;
    }
}
