package com.example.movie.mapper;

import com.example.movie.model.dto.MovieDto;
import com.example.movie.model.entity.Movie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieDto toDto(Movie movie);
    Movie toEntity(MovieDto dto);
}
