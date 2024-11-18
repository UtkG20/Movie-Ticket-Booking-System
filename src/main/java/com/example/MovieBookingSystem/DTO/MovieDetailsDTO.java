package com.example.MovieBookingSystem.DTO;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ComedyMovieDetailDTO.class, name = "comedy"),
        @JsonSubTypes.Type(value = HorrorMovieDetailDTO.class, name = "horror")
})
public class MovieDetailsDTO {

    protected String movieTitle;
    protected String genre;
}
