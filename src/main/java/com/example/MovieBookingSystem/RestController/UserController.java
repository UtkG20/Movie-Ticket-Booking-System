package com.example.MovieBookingSystem.RestController;

import com.example.MovieBookingSystem.DTO.UserDTO;
import com.example.MovieBookingSystem.Services.UserService;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("createUser")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO){
//        System.out.println(userDTO.getUserName()+" "+userDTO.getEmail());
        return this.userService.createUser(userDTO);
    }

    @GetMapping("getBookings")
    public ResponseEntity<?> getUserBookings(@RequestParam int userId){
        return this.userService.getUserBookings(userId);
    }
}
