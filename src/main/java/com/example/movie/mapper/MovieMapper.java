package com.example.movie.mapper;

import com.example.movie.model.dto.MovieDto;
import com.example.movie.model.entity.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public MovieDto toDto(Movie movie) {
        if (movie == null) {
            return null;
        }
        
        return MovieDto.builder()
                .title(movie.getTitle())
                .year(movie.getYear())
                .imdbId(movie.getImdbId())
                .build();
    }

    public Movie toEntity(MovieDto dto) {
        if (dto == null) {
            return null;
        }
        
        return Movie.builder()
                .title(dto.getTitle())
                .year(dto.getYear())
                .imdbId(dto.getImdbId())
                .build();
    }
}
