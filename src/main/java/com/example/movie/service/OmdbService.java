package com.example.movie.service;

import com.example.movie.model.dto.OmdbResponse;
import com.example.movie.model.entity.Movie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class OmdbService {
    
    private final RestTemplate restTemplate;
    private final String apiKey;
    private final String apiUrl;
    
    public OmdbService(@Value("${omdb.api.key}") String apiKey, 
                      @Value("${omdb.api.url}") String apiUrl) {
        this.apiKey = apiKey;
        this.apiUrl = apiUrl;
        this.restTemplate = new RestTemplate();
    }
    
    public OmdbResponse searchMovieByTitle(String title) {
        String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("apikey", apiKey)
                .queryParam("t", title)
                .queryParam("plot", "full")
                .build()
                .toUriString();
        
        return restTemplate.getForObject(url, OmdbResponse.class);
    }
    
    public OmdbResponse searchMovieByImdbId(String imdbId) {
        String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("apikey", apiKey)
                .queryParam("i", imdbId)
                .queryParam("plot", "full")
                .build()
                .toUriString();
        
        return restTemplate.getForObject(url, OmdbResponse.class);
    }
    
    public Movie convertOmdbResponseToMovie(OmdbResponse omdbResponse) {
        if (omdbResponse == null || !"True".equals(omdbResponse.getResponse())) {
            return null;
        }
        
        Movie movie = new Movie();
        movie.setTitle(omdbResponse.getTitle());
        movie.setYear(omdbResponse.getYear());
        movie.setImdbId(omdbResponse.getImdbID());
        movie.setRated(omdbResponse.getRated());
        movie.setReleased(omdbResponse.getReleased());
        movie.setRuntime(omdbResponse.getRuntime());
        movie.setGenre(omdbResponse.getGenre());
        movie.setDirector(omdbResponse.getDirector());
        movie.setWriter(omdbResponse.getWriter());
        movie.setActors(omdbResponse.getActors());
        movie.setPlot(omdbResponse.getPlot());
        movie.setLanguage(omdbResponse.getLanguage());
        movie.setCountry(omdbResponse.getCountry());
        movie.setAwards(omdbResponse.getAwards());
        movie.setPoster(omdbResponse.getPoster());
        movie.setMetascore(omdbResponse.getMetascore());
        movie.setImdbRating(omdbResponse.getImdbRating());
        movie.setImdbVotes(omdbResponse.getImdbVotes());
        movie.setBoxOffice(omdbResponse.getBoxOffice());
        movie.setProduction(omdbResponse.getProduction());
        
        return movie;
    }
}
