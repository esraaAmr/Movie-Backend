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
        
        MovieDto dto = new MovieDto();
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setYear(movie.getYear());
        dto.setImdbId(movie.getImdbId());
        dto.setRated(movie.getRated());
        dto.setReleased(movie.getReleased());
        dto.setRuntime(movie.getRuntime());
        dto.setGenre(movie.getGenre());
        dto.setDirector(movie.getDirector());
        dto.setWriter(movie.getWriter());
        dto.setActors(movie.getActors());
        dto.setPlot(movie.getPlot());
        dto.setLanguage(movie.getLanguage());
        dto.setCountry(movie.getCountry());
        dto.setAwards(movie.getAwards());
        dto.setPoster(movie.getPoster());
        dto.setMetascore(movie.getMetascore());
        dto.setImdbRating(movie.getImdbRating());
        dto.setImdbVotes(movie.getImdbVotes());
        dto.setBoxOffice(movie.getBoxOffice());
        dto.setProduction(movie.getProduction());
        
        return dto;
    }

    public Movie toEntity(MovieDto dto) {
        if (dto == null) {
            return null;
        }
        
        Movie movie = new Movie();
        movie.setId(dto.getId());
        movie.setTitle(dto.getTitle());
        movie.setYear(dto.getYear());
        movie.setImdbId(dto.getImdbId());
        movie.setRated(dto.getRated());
        movie.setReleased(dto.getReleased());
        movie.setRuntime(dto.getRuntime());
        movie.setGenre(dto.getGenre());
        movie.setDirector(dto.getDirector());
        movie.setWriter(dto.getWriter());
        movie.setActors(dto.getActors());
        movie.setPlot(dto.getPlot());
        movie.setLanguage(dto.getLanguage());
        movie.setCountry(dto.getCountry());
        movie.setAwards(dto.getAwards());
        movie.setPoster(dto.getPoster());
        movie.setMetascore(dto.getMetascore());
        movie.setImdbRating(dto.getImdbRating());
        movie.setImdbVotes(dto.getImdbVotes());
        movie.setBoxOffice(dto.getBoxOffice());
        movie.setProduction(dto.getProduction());
        
        return movie;
    }
}
