package com.movies.repository;

import com.movies.mock.MockMovie;

import com.movies.model.MoviesResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

@SpringBootTest
public class RequestMoviesTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private RequestMovies requestMovies;

    @Value("${api.movies.search}")
    private String moviesUrl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        requestMovies = new RequestMovies(restTemplate, moviesUrl);
    }

    @Test
    public void testFindDirectorsByThreshold() {
        Set<String> expectedDirectors = new TreeSet<>(Arrays.asList("Clint Eastwood", "Clint Sanchez"));
        MoviesResult moviesResult = MockMovie.buildMoviesResult();
        when(restTemplate.getForObject(anyString(), any(Class.class)))
                .thenReturn(moviesResult);
        Set<String> directors = requestMovies.getDirectors(2);
        assertEquals(expectedDirectors, directors);
    }

    @Test
    public void testFindDirectorsByThresholdWhenDataIsNull() {
        Set<String> expectedEmpty = new TreeSet<>();
        MoviesResult moviesResult = MockMovie.buildMoviesResultWithNullData();
        when(restTemplate.getForObject(anyString(), any(Class.class)))
                .thenReturn(moviesResult);
        Set<String> directors = requestMovies.getDirectors(2);
        assertEquals(expectedEmpty, directors);
    }
}
