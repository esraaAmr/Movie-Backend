package com.example.movie.service;

import com.example.movie.model.entity.Movie;
import com.example.movie.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie addMovie(Movie movie) {
        movieRepository.findByImdbId(movie.getImdbId())
                .stream()
                .findAny()
                .ifPresent(m -> {
                    throw new IllegalStateException("Movie already exists");
                });

        return movieRepository.save(movie);
    }


    public void removeMovie(Long id) {
        movieRepository.deleteById(id);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    public List<Movie> searchByTitle(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title);
    }

}
