package com.movies.repository;

import com.movies.model.MoviePageable;
import com.movies.model.MoviesResult;
import com.movies.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class RequestMovies {
    private final RestTemplate restTemplate;
    private final String moviesUrl;
    private final Integer  PAGE_INCREASE = 1;
    private final Integer DEFAULT_END_PAGE = 1;


    @Autowired
    public RequestMovies(
            RestTemplate restTemplate,
            @Value("${api.movies.search}") String moviesUrl) {
        this.restTemplate = restTemplate;
        this.moviesUrl = moviesUrl;
    }

    public Set<String> getDirectors(final Integer threshold) {
        return findDirectorsByThreshold(
                threshold,
                MoviePageable
                        .builder()
                        .pageStart(PAGE_INCREASE)
                        .pageEnd(DEFAULT_END_PAGE)
                        .directors(new HashMap<>())
                        .build());
    }

    private Set<String> findDirectorsByThreshold(final Integer threshold, MoviePageable moviePageable) {
        while (moviePageable.getPageStart() <= moviePageable.getPageEnd()) {
            MoviesResult moviesResult = requestMovies(moviePageable.getPageStart());
            if(moviesResult !=null && moviesResult.data() != null && !moviesResult.data().isEmpty()){
                buildMapDirectors(moviePageable, moviesResult);
                findDirectorsByThreshold(threshold, moviePageable);
            } else{
                break;
            }

        }
        return moviePageable.getDirectors()
                .entrySet()
                .stream()
                .filter(e -> e.getValue() > threshold)
                .map(Map.Entry::getKey)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    private void buildMapDirectors(MoviePageable moviePageable, MoviesResult moviesResult) {
        Map<String, Long> memoryDirectors = moviePageable.getDirectors();
        Map<String, Long> directors = moviesResult.data().stream()
                .collect(Collectors.groupingBy(Movie::Director, Collectors.counting()));

        directors.entrySet().forEach(item -> {
            if (memoryDirectors.containsKey(item.getKey())) {
                memoryDirectors.compute(item.getKey(), (k, counting) -> (counting + item.getValue()));
            } else {
                memoryDirectors.put(item.getKey(), item.getValue());
            }
        });
        moviePageable.setPageStart(moviePageable.getPageStart() + PAGE_INCREASE);
        moviePageable.setPageEnd(moviesResult.total_pages());

    }

    private MoviesResult requestMovies(Integer pageStart) {
        return restTemplate.getForObject(moviesUrl + pageStart, MoviesResult.class);
    }

}
