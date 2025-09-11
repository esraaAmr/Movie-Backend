package com.example.movie.model.dto;

import lombok.Data;

@Data
public class MovieDto {
    private Long id;
    private String title;
    private String year;
    private String imdbId;
    private String rated;
    private String released;
    private String runtime;
    private String genre;
    private String director;
    private String writer;
    private String actors;
    private String plot;
    private String language;
    private String country;
    private String awards;
    private String poster;
    private String metascore;
    private String imdbRating;
    private String imdbVotes;
    private String boxOffice;
    private String production;
}
