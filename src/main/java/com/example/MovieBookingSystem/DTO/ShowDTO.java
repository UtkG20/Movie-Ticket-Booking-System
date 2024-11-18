package com.example.MovieBookingSystem.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ShowDTO {

    @NonNull
    private int movieId;

    @NonNull
    private LocalDateTime showTime;

    @NonNull
    private int noOfSeats;
}
