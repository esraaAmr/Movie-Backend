package com.example.movie.mapper;

import com.example.movie.model.dto.RatingDto;
import com.example.movie.model.entity.Rating;
import org.springframework.stereotype.Component;

@Component
public class RatingMapper {

    public RatingDto toDto(Rating rating) {
        if (rating == null) {
            return null;
        }
        
        return RatingDto.builder()
                .rating(rating.getRating())
                .userId(rating.getUser() != null ? rating.getUser().getId() : null)
                .movieId(rating.getMovie() != null ? rating.getMovie().getId() : null)
                .build();
    }

    public Rating toEntity(RatingDto dto) {
        if (dto == null) {
            return null;
        }
        
        return Rating.builder()
                .rating(dto.getRating())
                // Note: For entity creation, you would typically need to fetch the related entities
                // This is usually handled in the service layer
                // For now, we'll leave the relationships null and let the service handle them
                .build();
    }
}
