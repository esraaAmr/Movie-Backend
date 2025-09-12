package com.example.movie.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// For POST only
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMovieDto {
    private String title;
    private String year;
    private String imdbId;
}

