package com.example.movie.controller;

import com.example.movie.model.dto.MovieDto;
import com.example.movie.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<MovieDto> addMovie(@RequestBody MovieDto movieDto) {
        return ResponseEntity.ok(movieService.addMovieDto(movieDto));
    }

    @GetMapping
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMoviesDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable Long id) {
        return movieService.getMovieByIdDto(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieDto>> searchMovies(@RequestParam String title) {
        return ResponseEntity.ok(movieService.searchMoviesInDatabase(title));
    }

    @GetMapping("/omdb/search")
    public ResponseEntity<MovieDto> searchMovieFromOmdb(@RequestParam String title) {
        MovieDto movie = movieService.searchMovieFromOmdb(title);
        if (movie != null) {
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/omdb/import")
    public ResponseEntity<MovieDto> importMovieFromOmdb(@RequestParam String imdbId) {
        try {
            MovieDto movie = movieService.importMovieFromOmdb(imdbId);
            if (movie != null) {
                return ResponseEntity.ok(movie);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeMovie(@PathVariable Long id) {
        movieService.removeMovie(id);
        return ResponseEntity.noContent().build();
    }
}
