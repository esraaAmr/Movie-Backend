package com.example.movie.mapper;

import com.example.movie.model.dto.RatingDto;
import com.example.movie.model.entity.Rating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RatingMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "movieId", source = "movie.id")
    RatingDto toDto(Rating rating);

    @Mapping(target = "user", ignore = true) // handle manually in service
    @Mapping(target = "movie", ignore = true) // handle manually in service
    Rating toEntity(RatingDto dto);
}
