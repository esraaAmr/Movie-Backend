package com.example.movie.service;

import com.example.movie.mapper.MovieMapper;
import com.example.movie.model.dto.MovieDto;
import com.example.movie.model.dto.OmdbResponse;
import com.example.movie.model.entity.Movie;
import com.example.movie.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final OmdbService omdbService;
    private final MovieMapper movieMapper;

    public MovieService(MovieRepository movieRepository, OmdbService omdbService, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.omdbService = omdbService;
        this.movieMapper = movieMapper;
    }

    public MovieDto addMovieDto(MovieDto movieDto) {
        Movie movie = movieMapper.toEntity(movieDto);

        if (movie.getImdbId() != null && movieRepository.findByImdbId(movie.getImdbId()).isPresent()) {
            throw new IllegalStateException("Movie already exists");
        }
        Movie savedMovie = movieRepository.save(movie);
        return movieMapper.toDto(savedMovie);
    }

    public void removeMovie(Long id) {
        movieRepository.deleteById(id);
    }

    public List<MovieDto> getAllMoviesDto() {
        return movieRepository.findAll()
                .stream()
                .map(movieMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<MovieDto> getMovieByIdDto(Long id) {
        return movieRepository.findById(id)
                .map(movieMapper::toDto);
    }

    public List<MovieDto> searchMoviesInDatabase(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(movieMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Import a single movie from OMDb using imdbId and persist into DB.
     * Throws IllegalStateException if movie already exists.
     * Throws RuntimeException for OMDb errors.
     */
    public MovieDto importMovieFromOmdb(String imdbId) {
        OmdbResponse omdbResponse = omdbService.searchMovieByImdbId(imdbId);

        if (omdbResponse == null) {
            throw new RuntimeException("OMDb returned null for imdbId: " + imdbId);
        }
        if (!"True".equalsIgnoreCase(omdbResponse.getResponse())) {
            throw new RuntimeException("OMDb error for imdbId " + imdbId + ": " + omdbResponse.getResponse());
        }

        Movie movie = omdbService.convertOmdbResponseToMovie(omdbResponse);

        if (movie.getImdbId() == null) {
            throw new RuntimeException("OMDb response missing imdbID for imdbId: " + imdbId);
        }

        if (movieRepository.findByImdbId(movie.getImdbId()).isPresent()) {
            throw new IllegalStateException("Movie already exists");
        }

        Movie savedMovie = movieRepository.save(movie);
        return movieMapper.toDto(savedMovie);
    }
}
