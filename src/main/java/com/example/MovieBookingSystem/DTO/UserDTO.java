package com.example.MovieBookingSystem.DTO;


import jakarta.validation.constraints.Email;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NonNull
    private String userName;

    @NonNull
    @Email(message = "Email should be valid")
    private String email;
}
