package com.movies.service;

import com.movies.repository.RequestMovies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class MovieService {

    private final RequestMovies requestMovies;

    @Autowired
    public MovieService(RequestMovies requestMovies){
        this.requestMovies = requestMovies;
    }

    public Set<String> getDirectors(final Integer threshold){
        return requestMovies.getDirectors(threshold);

    }
}
