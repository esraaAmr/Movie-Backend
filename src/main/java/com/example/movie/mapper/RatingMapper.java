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
        
        RatingDto dto = new RatingDto();
        dto.setId(rating.getId());
        dto.setRating(rating.getRating());
        
        // Map related entity IDs
        if (rating.getUser() != null) {
            dto.setUserId(rating.getUser().getId());
            dto.setUsername(rating.getUser().getUsername());
        }
        
        if (rating.getMovie() != null) {
            dto.setMovieId(rating.getMovie().getId());
            dto.setMovieTitle(rating.getMovie().getTitle());
        }
        
        return dto;
    }

    public Rating toEntity(RatingDto dto) {
        if (dto == null) {
            return null;
        }
        
        Rating rating = new Rating();
        rating.setId(dto.getId());
        rating.setRating(dto.getRating());
        
        // Note: For entity creation, you would typically need to fetch the related entities
        // This is usually handled in the service layer
        // For now, we'll leave the relationships null and let the service handle them
        
        return rating;
    }
}
