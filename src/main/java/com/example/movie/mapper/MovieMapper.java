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
                .id(movie.getId())
                .title(movie.getTitle())
                .year(movie.getYear())
                .imdbId(movie.getImdbId())
                .rated(movie.getRated())
                .released(movie.getReleased())
                .runtime(movie.getRuntime())
                .genre(movie.getGenre())
                .director(movie.getDirector())
                .writer(movie.getWriter())
                .actors(movie.getActors())
                .plot(movie.getPlot())
                .language(movie.getLanguage())
                .country(movie.getCountry())
                .awards(movie.getAwards())
                .poster(movie.getPoster())
                .metascore(movie.getMetascore())
                .imdbRating(movie.getImdbRating())
                .imdbVotes(movie.getImdbVotes())
                .boxOffice(movie.getBoxOffice())
                .production(movie.getProduction())
                .build();
    }

    public Movie toEntity(MovieDto dto) {
        if (dto == null) {
            return null;
        }
        
        return Movie.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .year(dto.getYear())
                .imdbId(dto.getImdbId())
                .rated(dto.getRated())
                .released(dto.getReleased())
                .runtime(dto.getRuntime())
                .genre(dto.getGenre())
                .director(dto.getDirector())
                .writer(dto.getWriter())
                .actors(dto.getActors())
                .plot(dto.getPlot())
                .language(dto.getLanguage())
                .country(dto.getCountry())
                .awards(dto.getAwards())
                .poster(dto.getPoster())
                .metascore(dto.getMetascore())
                .imdbRating(dto.getImdbRating())
                .imdbVotes(dto.getImdbVotes())
                .boxOffice(dto.getBoxOffice())
                .production(dto.getProduction())
                .build();
    }
}
