package com.example.movie.controller;

import com.example.movie.model.dto.RatingDto;
import com.example.movie.service.RatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping
    public ResponseEntity<RatingDto> addRating(@RequestBody RatingDto ratingDto) {
        try {
            return ResponseEntity.ok(ratingService.addRatingDto(ratingDto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<RatingDto>> getRatingsByMovie(@PathVariable Long movieId) {
        return ResponseEntity.ok(ratingService.getRatingsByMovieDto(movieId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RatingDto>> getRatingsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(ratingService.getRatingsByUserDto(userId));
    }
}
