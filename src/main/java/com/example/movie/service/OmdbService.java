package com.example.movie.service;

import com.example.movie.mapper.OmdbMapper;
import com.example.movie.model.dto.OmdbResponse;
import com.example.movie.model.dto.OmdbSearchResponse;
import com.example.movie.model.dto.OmdbSearchResult;
import com.example.movie.model.entity.Movie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

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
        this.apiUrl = apiUrl.endsWith("/") ? apiUrl : apiUrl + "/";
        this.omdbMapper = omdbMapper;
        this.restTemplate = new RestTemplate();
    }

    /**
     * Search OMDb by imdb id and return full details mapped to OmdbResponse.
     */
    public OmdbResponse searchMovieByImdbId(String imdbId) {
        String url = apiUrl + "?apikey=" + apiKey + "&i=" + URLEncoder.encode(imdbId, StandardCharsets.UTF_8) + "&plot=full";
        // raw JSON logging could be kept during debugging; removed here for clarity
        return restTemplate.getForObject(url, OmdbResponse.class);
    }

    /**
     * Search OMDb by keyword (s=) and return list of search results.
     */
    public List<OmdbSearchResult> searchMoviesListFromOmdb(String keyword) {
        String url = apiUrl + "?apikey=" + apiKey + "&s=" + URLEncoder.encode(keyword, StandardCharsets.UTF_8);
        OmdbSearchResponse response = restTemplate.getForObject(url, OmdbSearchResponse.class);
        if (response != null && "True".equalsIgnoreCase(response.getResponse())) {
            return response.getSearch();
        }
        return Collections.emptyList();
    }

    /**
     * Convert a detailed OmdbResponse to your Movie entity using mapper.
     */
    public Movie convertOmdbResponseToMovie(OmdbResponse omdbResponse) {
        return omdbMapper.toEntity(omdbResponse);
    }
}
