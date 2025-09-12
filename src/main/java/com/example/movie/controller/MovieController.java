package com.example.movie.controller;

import com.example.movie.model.dto.MovieDto;
import com.example.movie.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@Tag(name = "Movies", description = "Endpoints for managing movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @Operation(summary = "Add a new movie")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Movie added successfully"),
            @ApiResponse(responseCode = "409", description = "Movie already exists")
    })
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

    @Operation(summary = "Get all movies")
    @GetMapping
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMoviesDto());
    }

    @Operation(summary = "Get a movie by ID")
    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable Long id) {
        return movieService.getMovieByIdDto(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Search movies by title in database")
    @GetMapping("/search")
    public ResponseEntity<List<MovieDto>> searchMovies(@RequestParam String title) {
        return ResponseEntity.ok(movieService.searchMoviesInDatabase(title));
    }

    @Operation(summary = "Search a movie from OMDb API by title")
    @GetMapping("/omdb/search")
    public ResponseEntity<MovieDto> searchMovieFromOmdb(@RequestParam String title) {
        MovieDto movie = movieService.searchMovieFromOmdb(title);
        if (movie != null) {
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Import a movie from OMDb API using IMDb ID")
    @PostMapping("/omdb/import")
    public ResponseEntity<?> importMovieFromOmdb(@RequestParam String imdbId) {
        try {
            MovieDto movie = movieService.importMovieFromOmdb(imdbId);
            return ResponseEntity.ok(movie);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(409).body("Movie already exists");
        } catch (RuntimeException e) {
            return ResponseEntity.status(502).body(e.getMessage());
        }
    }

    @Operation(summary = "Remove a movie by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeMovie(@PathVariable Long id) {
        movieService.removeMovie(id);
        return ResponseEntity.noContent().build();
    }
}
