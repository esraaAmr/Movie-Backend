package com.example.movie.service;

import com.example.movie.mapper.OmdbMapper;
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
    private final OmdbMapper omdbMapper;
    
    public OmdbService(@Value("${omdb.api.key}") String apiKey, 
                      @Value("${omdb.api.url}") String apiUrl,
                      OmdbMapper omdbMapper) {
        this.apiKey = apiKey;
        this.apiUrl = apiUrl;
        this.omdbMapper = omdbMapper;
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
        return omdbMapper.toEntity(omdbResponse);
    }
}
