package com.movies.model;

import java.util.List;

public record MoviesResult(
        int page,
        int per_page,
        int total,
        int total_pages,
        List<Movie> data
) {
}
