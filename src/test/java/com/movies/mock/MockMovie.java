package com.movies.mock;

import com.movies.model.Movie;
import com.movies.model.MoviesResult;

import java.util.ArrayList;
import java.util.List;

public class MockMovie {

    public static MoviesResult buildMoviesResult() {
        List<Movie> movieList = new ArrayList<>();
        for (int j = 0; j < 5; j++) {
            Movie movie = new Movie("American Sniper",
                    "2014",
                    "R",
                    "16 Jan 2015",
                    "133 min",
                    "Action, Biography, Drama",
                    "Clint " + (j%2==0 ? "Eastwood" : "Sanchez"),
                    "Jason Hall, Chris Kyle, Scott McEwen",
                    "Bradley Cooper, Sienna Miller, Kyle Gallner");
            movieList.add(movie);
        }
        return new MoviesResult(1, 10, 26, 3, movieList);
    }

    public static MoviesResult buildMoviesResultWithNullData() {
        return new MoviesResult(1, 10, 26, 3, null);
    }

}
