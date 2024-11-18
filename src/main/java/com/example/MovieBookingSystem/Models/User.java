package com.example.MovieBookingSystem.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @NonNull
    private String userName;

    @NonNull
    @Email(message = "Email should be valid")
    @Column(unique = true)
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Booking> bookings;

    public User(String userName, String email){
        this.userName = userName;
        this.email = email;
        this.bookings = new ArrayList<>();

    }
}
