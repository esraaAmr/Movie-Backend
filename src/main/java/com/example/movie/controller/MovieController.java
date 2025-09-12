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
    public ResponseEntity<MovieDto> addMovie(@RequestBody MovieDto createMovieDto) {
        MovieDto movieDto = MovieDto.builder()
                .title(createMovieDto.getTitle())
                .year(createMovieDto.getYear())
                .imdbId(createMovieDto.getImdbId())
                .build();
        try {
            return ResponseEntity.ok(movieService.addMovieDto(movieDto));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(409).build();
        }
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
    public ResponseEntity<?> importMovieFromOmdb(@RequestParam String imdbId) {
        try {
            MovieDto movie = movieService.importMovieFromOmdb(imdbId);
            return ResponseEntity.ok(movie);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(409).body("Movie already exists");
        } catch (RuntimeException e) {
            // OMDb error or null
            return ResponseEntity.status(502).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeMovie(@PathVariable Long id) {
        movieService.removeMovie(id);
        return ResponseEntity.noContent().build();
    }
}
