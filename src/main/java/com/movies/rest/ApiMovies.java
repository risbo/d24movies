package com.movies.rest;

import com.movies.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class ApiMovies {

    private final MovieService movieService;

    @Autowired
    public ApiMovies(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/directors")
    @Operation(summary = "Get list of directors with movies above a certain threshold", description = "Returns a list of directors whose movies exceed the specified threshold")
    public ResponseEntity<Object> getDirectors(
            @Parameter(description = "Threshold for the number of movies", required = true) @RequestParam int threshold) {

        final Set<String> directors = movieService.getDirectors(threshold);
        if(directors.isEmpty()){
            return new ResponseEntity<>(
                    "Sorry, no results were found.", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(directors, HttpStatus.ACCEPTED);
    }
}
