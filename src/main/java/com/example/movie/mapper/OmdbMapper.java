package com.example.movie.mapper;

import com.example.movie.model.dto.OmdbResponse;
import com.example.movie.model.entity.Movie;
import org.springframework.stereotype.Component;

@Component
public class OmdbMapper {

    public Movie toEntity(OmdbResponse omdbResponse) {
        if (omdbResponse == null || !"True".equals(omdbResponse.getResponse())) {
            return null;
        }
        
        return Movie.builder()
                .title(omdbResponse.getTitle())
                .year(omdbResponse.getYear())
                .imdbId(omdbResponse.getImdbID())
                .rated(omdbResponse.getRated())
                .released(omdbResponse.getReleased())
                .runtime(omdbResponse.getRuntime())
                .genre(omdbResponse.getGenre())
                .director(omdbResponse.getDirector())
                .writer(omdbResponse.getWriter())
                .actors(omdbResponse.getActors())
                .plot(omdbResponse.getPlot())
                .language(omdbResponse.getLanguage())
                .country(omdbResponse.getCountry())
                .awards(omdbResponse.getAwards())
                .poster(omdbResponse.getPoster())
                .metascore(omdbResponse.getMetascore())
                .imdbRating(omdbResponse.getImdbRating())
                .imdbVotes(omdbResponse.getImdbVotes())
                .boxOffice(omdbResponse.getBoxOffice())
                .production(omdbResponse.getProduction())
                .build();
    }
}
