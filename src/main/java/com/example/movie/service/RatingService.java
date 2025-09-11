package com.example.movie.service;

import com.example.movie.mapper.RatingMapper;
import com.example.movie.model.dto.RatingDto;
import com.example.movie.model.entity.Movie;
import com.example.movie.model.entity.Rating;
import com.example.movie.model.entity.User;
import com.example.movie.repository.MovieRepository;
import com.example.movie.repository.RatingRepository;
import com.example.movie.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final RatingMapper ratingMapper;

    public RatingService(RatingRepository ratingRepository, 
                        UserRepository userRepository, 
                        MovieRepository movieRepository, 
                        RatingMapper ratingMapper) {
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.ratingMapper = ratingMapper;
    }

    public RatingDto addRatingDto(RatingDto ratingDto) {
        // Fetch related entities
        User user = userRepository.findById(ratingDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Movie movie = movieRepository.findById(ratingDto.getMovieId())
                .orElseThrow(() -> new IllegalArgumentException("Movie not found"));
        
        Rating rating = ratingMapper.toEntity(ratingDto);
        rating.setUser(user);
        rating.setMovie(movie);
        
        Rating savedRating = ratingRepository.save(rating);
        return ratingMapper.toDto(savedRating);
    }

    public List<RatingDto> getRatingsByMovieDto(Long movieId) {
        return ratingRepository.findByMovieId(movieId)
                .stream()
                .map(ratingMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<RatingDto> getRatingsByUserDto(Long userId) {
        return ratingRepository.findByUserId(userId)
                .stream()
                .map(ratingMapper::toDto)
                .collect(Collectors.toList());
    }

}
