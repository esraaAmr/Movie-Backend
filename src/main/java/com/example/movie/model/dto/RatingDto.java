package com.example.movie.model.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class RatingDto {
    private Long id;
    private Long userId;
    private Long movieId;
    private BigDecimal rating;
    
    // Optional: Include related entity data for display
    private String username;
    private String movieTitle;
}
